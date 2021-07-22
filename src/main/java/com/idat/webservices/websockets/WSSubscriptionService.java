package com.idat.webservices.websockets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
public class WSSubscriptionService {

	@Getter
	private Map<String, WebSocketClient> clients;

	@Getter
	private Map<String, Channel> channels;

	private WSSubscriptionService() {
		clients = new ConcurrentHashMap<>();
		channels = new ConcurrentHashMap<>();
	}

	public void addClient(WebSocketClient client) {
		if (client.getSession().isOpen())
			clients.put(client.getSession().getId(), client);
	}

	public void removeClient(String clientId) {
		if (clients.get(clientId).getSession().isOpen())
			clients.remove(clientId);
	}

	public boolean channelExists(String channelName) {
		return channels.containsKey(channelName);
	}

	public Channel addChannel(String channelName) {
		Channel channel = new Channel(channelName);
		channels.put(channelName, channel);
		return channel;
	}

}
