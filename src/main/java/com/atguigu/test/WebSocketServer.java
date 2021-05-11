package com.atguigu.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/ws/asset")
@Component
@Slf4j
public class WebSocketServer {

    @PostConstruct
    public void init(){
        System.out.println("websocket 加载");
    }
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的session对象。
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<Session>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session){
        sessionSet.add(session);
        int cut = OnlineCount.incrementAndGet();//在线数加1
        log.info("有连接加入，当前连接数为：{}",cut);
        SendMessage(session,"连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        sessionSet.remove(session);
        int cut = OnlineCount.decrementAndGet();
        log.info("有链接关闭，当前连接数为：{}",cut);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("来自客户端的消息：{}",message);
        SendMessage(session,"收到消息，消息内容:"+message);
    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session,Throwable error){
        log.error("发生错误：{}，Session ID:{}",error.getMessage(),session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化
     */
    public static void SendMessage(Session session,String message){
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server,Session ID=%s)",message,session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     */
    public static void BroadCastInfo(String message){
        for(Session session:sessionSet){
            if(session.isOpen()){
                SendMessage(session,message);
            }
        }
    }

    /**
     * 指定Session发送消息
     */
    public static void SendMesage(String message,String sessionId){
        Session session = null;
        for (Session s : sessionSet){
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }
        if (session != null){
            SendMessage(session,message);
        }else{
            log.warn("没有找到你指定ID的会话：{}",sessionId);
        }

    }







}
