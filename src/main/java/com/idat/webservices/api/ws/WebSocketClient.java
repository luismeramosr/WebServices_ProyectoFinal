package com.idat.webservices.api.ws;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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

}
