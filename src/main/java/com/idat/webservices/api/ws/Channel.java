package com.idat.webservices.api.ws;

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

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import lombok.Getter;

public class Channel {

	@Getter
	private String name;
	@Getter
	private Map<String, WebSocketClient> subs;
	private Gson gson;
	//private static Channel instance;

	//public static Channel getInstance(String name) {
	//	if (instance == null)
	//		instance = new Channel(name);
	//	return instance;
	//}

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
		Console.log(subs.size());
		Console.log(String.format("User has subscribed: %s", sub));
	}

	public void unSubscribe(WebSocketClient sub) {
		if (subs.containsValue(sub)) {
			subs.remove(sub.getSession().getId());
			Console.log("User unsubscribed: %s", sub);
		}
	}

	public void notifySubscribers(WSMessage msg) {

	}

	public void publish(WSMessage msg) {

		try {
			TextMessage payload = new TextMessage(gson.toJson(msg, WSMessage.class));
			Console.log(String.format("Payload: %s", payload.getPayload()));
			for (WebSocketClient sub : subs.values()) {
				if (sub != null && sub.getSession().isOpen()) {
					sub.getSession().sendMessage(payload);
				}
			}
		} catch (IOException err) {
			err.printStackTrace();
		} 
	}

}
