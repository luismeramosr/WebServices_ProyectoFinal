package com.idat.webservices.util;

public class Console {
	public static void log(Object... args)	{
		for (Object arg : args) {
			System.out.println(arg);
		}
	}
}
