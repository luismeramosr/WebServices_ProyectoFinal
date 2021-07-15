package com.idat.webservices.api.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.idat.webservices.persistence.models.Item;
import com.idat.webservices.util.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WSMessageHandler {

	@Autowired
	private WSSubscriptionService subService;
	private Gson gson;
	private Map<String, Runnable> endpoints;
	private WebSocketClient client;
	private WSMessage msg;

	private WSMessageHandler() {
		gson = new Gson();
		endpoints = new HashMap<>();
		msg = WSMessage.buildMessage("any");
		endpoints.put("Pong", this::onPong);
		endpoints.put("Item", this::subscribeToItem);
	}

	public void handleMessage(String message, WebSocketClient _client) throws IOException {
		client = _client;
		Console.log(message);
		JsonObject jo = gson.fromJson(message, JsonObject.class);
		msg.setId(jo.get("id").getAsString());
		msg.setBody(jo.get("body").getAsJsonObject());
		endpoints.get(msg.getId()).run();
	}

	private void onPong() {
		client.getTimeOutTimer().cancel();
		client.getTimeOutTimer().purge();
	}

	private void subscribeToItem() {
		Item item = gson.fromJson(msg.getBody(), Item.class);
		Channel channel = subService.getChannels().get(item.getBarcode());
		if (channel != null) {
			channel.subscribe(client);
		} else {
			subService.addChannel(item.getBarcode()).subscribe(client);
		}
		Console.log(String.format("Channel created: %s", subService.getChannels().get(item.getBarcode()).getName()));
	}
}
