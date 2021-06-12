package com.idat.webservices.api.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketService {

	private List<WebSocketSession> sessions;
	
	public WebSocketService() {
		this.sessions = new CopyOnWriteArrayList<WebSocketSession>();
	}

	public void addSession(WebSocketSession session) {
		sessions.add(session);
	}

	public void removeSession(WebSocketSession session) {
		sessions.remove(session);
	}

	public void broadcastStock(String newItemStock) throws IOException {
		for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage(
				String.format("{\"itemUpdated:%s\"}", newItemStock))
			);
		}
	}

	public void onMessage(WebSocketSession session, TextMessage message) {
		// TODO: Receive Stock update message and respond accordingly
	}

}
