package com.idat.webservices.websockets;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class WebSocketClient {
	
	private WebSocketSession session;
	private Timer timeOutTimer;
	private ScheduledExecutorService pingExecutorService;

	public WebSocketClient(WebSocketSession _session) {
		session = _session;
	}

	public void update(TextMessage data) {
		try {
			session.sendMessage(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
