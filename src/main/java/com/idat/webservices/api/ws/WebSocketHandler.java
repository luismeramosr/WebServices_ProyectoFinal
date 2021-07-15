package com.idat.webservices.api.ws;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.websocket.CloseReason;
import javax.websocket.OnMessage;
import javax.websocket.CloseReason.CloseCodes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.idat.webservices.util.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class WebSocketHandler extends TextWebSocketHandler {

	@Autowired
	private WSMessageHandler msgHandler;

	@Autowired
	public WSSubscriptionService subService;
	
	@Autowired
	public Gson gson;

@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		WebSocketClient client = new WebSocketClient(session);
		Console.log(String.format("User %s connected", session.getId()));
		subService.addClient(client);
		schedulePingMessages(client);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
		System.out.println(String.format("Error with session %s: %s", session, throwable.getMessage()));
		throwable.printStackTrace();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Console.log(String.format("User %s disconnected", session.getId()));
		subService.removeClient(session.getId());
	}

	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		msgHandler.handleMessage(message.getPayload(), subService.getClients().get(session.getId()));
	}

	private void schedulePingMessages(WebSocketClient client) {
		client.setPingExecutorService(Executors.newScheduledThreadPool(1));
		client.getPingExecutorService().scheduleAtFixedRate(() -> {
			scheduleDiconnection(client);
			try {
				WSMessage message = WSMessage.buildMessage("Ping");
				String payload = gson.toJson(message, WSMessage.class);
				client.getSession().sendMessage(new TextMessage(payload));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, 1, 5, TimeUnit.SECONDS);
	}

	private void scheduleDiconnection(WebSocketClient client) {
		client.setTimeOutTimer(new Timer());
		client.getTimeOutTimer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					client.getSession().close();
					Console.log("Client did not respond Ping");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 10000);
	}

}
