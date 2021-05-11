/*
package com.atguigu.conf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imserver/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    */
/** 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的 *//*

    private static int onlineCount = 0;
    */
/** concurrent包的线程安全set，用来存放每个客户端对应的MyWebSocket对象 *//*

    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    */
/** 与莫格客户端的连接会话，需要通过它来给客户端发送数据 *//*

    private Session session;
    */
/** 接收userId *//*

    private String userId;

    */
/**
     * 连接建立成功调用的方法
     *//*

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId){
        this.session = session;
        this.userId = userId;

        if(webSocketMap.contains(userId)){
            webSocketMap.remove(userId);
            //加入set中
            webSocketMap.put(userId,this);
        }else{
            //加入set中
            webSocketMap.put(userId,this);
            //在线数加1
            addOnlineCount();
        }

        log.info("用户连接："+userId+",当前在线人数为:"+getOnlineCount());
        try{
            sendMessage("连接成功");
        }catch (IOException e){
            log.error("用户:"+userId+",网络异常！！！");
        }
    }

    */
/**
     * 关闭连接调用的方法
     *//*

    @OnClose
    public void onClose(){
        if(webSocketMap.contains(userId)){
            //从set中删除
            webSocketMap.remove(userId);

            subOnlineCount();
        }
        log.info("用户退出："+userId+",当前在线人数为:"+getOnlineCount());
    }

    */
/**
     * 收到客户端调用后调用的方法
     *//*

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("用户消息："+userId+",报文:"+message);
        //可以群发
        //消息保存到数据库、redis
        if(StringUtils.isNotEmpty(message)){
            try{
                //解析发送的报文
                JSONObject object = JSON.parseObject(message);
                //追加发送人（防止串改）
                object.put("fromUserId",this.userId);
                String toUserId = object.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotEmpty(toUserId) && webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(object.toJSONString());
                }else{
                    log.error("请求的userId:"+toUserId+"不在该服务器上");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        log.error("用户错误："+this.userId+",原因："+error.getMessage());
        error.printStackTrace();
    }

    */
/**
     * 实现服务器主动推送
     *//*

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    */
/**
     * 发送自定义消息
     *//*

    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到："+userId+",报文："+message);
        if(StringUtils.isNotEmpty(userId) && webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户："+userId+",不在线");
        }
    }


    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}
*/
