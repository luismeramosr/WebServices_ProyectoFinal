package com.idat.webservices.util;

public class Helpers {
	
	public static void setTimeout(Runnable function, int timeout_seconds) {
		new Thread(() -> {
			try {
				Thread.sleep(timeout_seconds*1000);
				function.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

}
