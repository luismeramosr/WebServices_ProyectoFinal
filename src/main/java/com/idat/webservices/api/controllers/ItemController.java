package com.idat.webservices.api.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.idat.webservices.api.ws.Channel;
import com.idat.webservices.api.ws.WSMessage;
import com.idat.webservices.api.ws.WSSubscriptionService;
import com.idat.webservices.api.ws.WebSocketHandler;
import com.idat.webservices.domain.dto.Response;
import com.idat.webservices.persistence.models.Item;
import com.idat.webservices.persistence.services.ItemService;
import com.idat.webservices.util.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService service;

	@Autowired
	private WebSocketHandler ws;

	@Autowired
	private Gson gson;

	@GetMapping
	public ResponseEntity<Response<List<Item>>> getAll() {
		return new ResponseEntity<>(new Response<>(service.findAll()), HttpStatus.OK);
	}

	@GetMapping("{barcode}")
	public ResponseEntity<Response<Item>> findItemById(@PathVariable("barcode") String barcode) {
		Item item = service.findById(barcode).orElse(null);
		if (item != null)
			return new ResponseEntity<>(new Response<>(item), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(item, "No se pudo encontrar el item"), HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Response<Item>> addItem(@RequestBody Item item) {
		Item newItem = service.save(item).orElse(null);
		if (item != null)
			return new ResponseEntity<>(new Response<>(newItem), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(newItem, "Datos erróneos"), HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	public ResponseEntity<Response<Item>> updateItem(@RequestBody Item item) {
		Item newItem = service.update(item).orElse(null);
		WSMessage message = WSMessage.buildMessage("Item");
		message.setBody(gson.fromJson(gson.toJson(item), JsonObject.class));
		Channel publisher = ws.subService.getChannels().get(item.getBarcode());
		if (publisher != null)
			publisher.publish(message);
		if (newItem != null)
			return new ResponseEntity<>(new Response<>(newItem), HttpStatus.OK);
		else
			return new ResponseEntity<>(new Response<>(newItem, "Datos erróneos"), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{barcode}")
	public void deleteItem(@PathVariable("barcode") String barcode) {
		Item item = new Item();
		item.setBarcode(barcode);
		service.delete(item);
	}

}
