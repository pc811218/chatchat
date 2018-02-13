package chat.server.endpoint;

import java.util.Hashtable;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import base.generic.text.ChatMessage;

//Server端URL寫法，宣告參數名稱寫法: (EndPoint)/{param1}/{param2}/{param3}/......
@ServerEndpoint("/ChatServer/{account}/{name}")
public class ChatServer {
//	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	private static final Map<Session,String> allSessions = new Hashtable<Session,String>();
	
	//接收參數用法 : @PathParam("param1") String *variable*
	@OnOpen
	public void onOpen(@PathParam("account") String account,@PathParam("name") String name, Session userSession) {
		String user = name+"("+account+")";
		allSessions.put(userSession,user);
		sendToAll(String.format(ChatMessage.JOIN.val(), user));
		System.out.println(user + ": has connected");
		System.out.println(allSessions);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		sendToAll(String.format(ChatMessage.LEAVE.val(), allSessions.get(userSession)));
		allSessions.remove(userSession);
		//e.printStackTrace();
	}
	
	
    @OnMessage
    public void handleMessage(String msg, Session userSession) {
    	String user = allSessions.get(userSession);
    	sendToAll(String.format(ChatMessage.SPEAK.val(), user, msg));

    }
    
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		sendToAll(String.format(ChatMessage.LEAVE.val(), allSessions.get(userSession)));
		System.out.println(allSessions.get(userSession) + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
		allSessions.remove(userSession);
	}
	
	//傳送給全部人
	private void sendToAll(String message){
		for (Session session : allSessions.keySet()) {
			if (session.isOpen()) 
				session.getAsyncRemote().sendText(message);
			
		}
	}
	
	public static Map<Session,String> getAllUsers(){
		
		return allSessions;
	}
}
