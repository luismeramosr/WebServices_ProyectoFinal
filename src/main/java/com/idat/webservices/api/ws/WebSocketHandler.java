package com.idat.webservices.api.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnMessage;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	@Autowired
	private WebSocketService service;

	@Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
		System.out.println(String.format("Error with session %s: %s", session, throwable.getMessage()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		service.removeSession(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		service.addSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
		service.onMessage(session, jsonTextMessage);
		System.out.println(jsonTextMessage.getPayload());
    }
	
}
