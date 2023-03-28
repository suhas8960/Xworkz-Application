package com.xworkz.homestay.configuration;

import org.springframework.stereotype.Component;

@Component
public class Laptop {

	public Laptop() {
		System.out.println("laptop created");
	}

	public void test() {
		System.out.println("running test..");
	}

}
