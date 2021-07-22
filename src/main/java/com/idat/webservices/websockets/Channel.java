package com.idat.webservices.websockets;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.idat.webservices.persistence.services.ItemService;
import com.idat.webservices.util.Console;
import com.idat.webservices.util.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import lombok.Getter;

public class Channel {

	@Getter
	private String name;
	@Getter
	private Map<String, WebSocketClient> subs;
	private Gson gson;

	@Autowired
	Helpers helpers;

	public Channel(String _name) {
		name = _name;
		subs = new ConcurrentHashMap<>();
		gson = new Gson();
	}

	public void subscribe(WebSocketClient sub) {
		if (subs.containsValue(sub)) {
			Console.log("User already subscribed!");
		} else {
			subs.put(sub.getSession().getId(), sub);
		}
		Console.log(
			String.format("Users subscribed: %s",
			subs.size())
		);
	}

	public void unSubscribe(WebSocketClient sub) {
		if (subs.containsValue(sub)) {
			subs.remove(sub.getSession().getId());
			Console.log(
			String.format("Users subscribed: %s",
			subs.size())
		);
		}
	}

	public void notifySubscribers(WSMessage msg) {

	}

	public void publish(WSMessage msg) {
		TextMessage payload = new TextMessage(gson.toJson(msg, WSMessage.class));
		Console.log(String.format("Payload: %s", payload.getPayload()));
		for (WebSocketClient sub : subs.values()) {
			if (sub != null && sub.getSession().isOpen()) {
				sub.update(payload);
			}
		}
	}
}
