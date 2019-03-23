package com.cube.chat.netty;

import com.alibaba.fastjson.JSON;
import com.cube.chat.pojo.ChatRecord;
import com.cube.chat.pojo.vo.Message;
import com.cube.chat.service.ChatRecordService;
import com.cube.chat.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 *处理消息的handler
 * TextWebSocketFrame: 在nettyz中,是用于为webSocket专门处理文本对象,frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的Channel
    private static ChannelGroup clients=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:MM");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取从客户端传输过来的消息
        String text = msg.text();
        //判断消息的类型,根据不同的消息类型执行不同的处理
        System.out.println(text);
        Message message= JSON.parseObject(text,Message.class);

        //通过SpringUtils工具类获取spring上下文容器
        ChatRecordService chatRecordService = SpringUtil.getBean(ChatRecordService.class);

        Integer type=message.getType();

        switch(type){
            case 0:
                //当webSocket第一次Open时候,初始化channel,channel关联到userId
                String userId = message.getChatRecord().getUserid();
                //保存对应的channel
                UserChannelMap.put(userId,ctx.channel());
                System.out.println("建立用户:" + userId + "与通道" + ctx.channel().id() + "的关联");
                for (Channel client : clients) {
                    System.out.println("客户端连接id: "+client.id());
                }
                for(String uid: UserChannelMap.userChannelMap.keySet()){
                    System.out.println("用户id:"+uid+"\n\n");
                    System.out.println("ChannelId"+UserChannelMap.get(uid));
                }
                break;
            case 1:
                // 聊天记录保存到数据库,标记消息的签收状态[未签收]
                System.out.println("接收到用户消息");
                //将聊天消息保存到数据库
                ChatRecord chatRecord = message.getChatRecord();
                chatRecordService.insert(chatRecord);

                //获取通道 如果发送消息好友在线,可以直接将消息发送给好友
                Channel channel = UserChannelMap.get(chatRecord.getFriendid());
                if(channel != null){
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                }else{
                    //如果不在线,暂时不发送
                    System.out.println("用户 "+chatRecord.getFriendid()+"不在线");
                }
                break;
            case 2:
                // 签收消息,修改数据库中的消息签收状态[已签收]
                // 将消息记录设置为已读
                chatRecordService.updateStatusHasRead(message.getChatRecord().getId());
                break;
            case 3:
                // 接收心跳消息
                System.out.println("接收到心跳消息:" + JSON.toJSONString(message));
                break;
        }

    }

    /**
     * 当客户端连接服务器之后(打开连接)
     * 获取客户端的channel ,并且放入到channelGroup中去进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将channel 添加到客户端
        clients.add(ctx.channel());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved ChannelGroup会自带移除对应的channel
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        UserChannelMap.print();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //抛出移除时移除通道
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
        cause.printStackTrace();
    }
}
