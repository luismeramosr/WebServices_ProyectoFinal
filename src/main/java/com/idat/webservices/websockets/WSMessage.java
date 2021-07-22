package com.idat.webservices.websockets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class WSMessage {

	private String id;
	private JsonObject body;

	private WSMessage(String id, JsonObject body) {
		this.id = id;
		this.body = body;
	}

	public static WSMessage buildMessage(String _id) {
		return new WSMessage(_id, new JsonObject());
	}

}
