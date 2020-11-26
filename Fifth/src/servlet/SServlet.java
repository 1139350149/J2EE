package servlet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/ws/{user}")
public class SServlet {
    private String currentUser;

    private static Set<Session> sessions = new HashSet<>();

    //连接打开时执行
    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session) {
        currentUser = user;
        System.out.println("Connected ... " + session.getId());
        sessions.add(session);
        List<String> online =  new ArrayList<>();
        online.add(user);
        HashMap<String,ArrayList<String>> hs = new HashMap<>();
        hs.put("ol", (ArrayList<String>) online);

        System.out.println(user);
        System.out.println(hs);
    }

    //收到消息时执行
    @OnMessage
    public String onMessage(String message, Session session) throws IOException {
        System.out.println(currentUser + "：" + message);
        //发送给所有人
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getBasicRemote().sendText(currentUser+"说:"+message);
            }
        }
        return null;
    }

    //连接关闭时执行
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }

    //连接错误时执行
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}