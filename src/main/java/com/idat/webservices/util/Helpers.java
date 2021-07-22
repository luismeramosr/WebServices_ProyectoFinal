package com.idat.webservices.util;

import java.util.List;

import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.services.UserService;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class Helpers {
	
	public void setTimeout(Runnable function, int timeout_seconds) {
		new Thread(() -> {
			try {
				Thread.sleep(timeout_seconds*1000);
				function.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public int generateId(UserService service) {
		List<User> users = service.findAll();
		int lastId = users.get(users.size()-1).getId()+1;
		return lastId;
	}

	public void runInBackgroundThread(Runnable function) {
		new Thread(function).start();
	}

}
