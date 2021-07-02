package com.idat.webservices.api.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketService {

	private ConcurrentHashMap<String, WebSocketSession> sessions;
	private Gson parser;
	
	public WebSocketService() {
		this.sessions = new ConcurrentHashMap<>();
		this.parser = new Gson();
	}

	public void addSession(WebSocketSession session) {
		sessions.put(session.getId(), session);
	}

	public void removeSession(WebSocketSession session) {
		sessions.remove(session.getId());
	}

	public void broadcastMessage(String idSender, JsonObject message) {
		List<WebSocketSession> receivers = new ArrayList<>();
		receivers.addAll(sessions.values());
		for (WebSocketSession receiver : receivers) {
			sendMessageTo(receiver, 
				String.format("{\"user\": %s, \"message\": %s}", 
						message.get("user"), message.get("message")));
		}
	}

	public void onMessage(WebSocketSession sender, TextMessage message) {
		broadcastMessage(sender.getId(), parser.fromJson(message.getPayload(), JsonObject.class));
	}

	public void sendMessageTo(WebSocketSession receiver, String message) {
		try {
			receiver.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
