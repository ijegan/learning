package com.example.models;

public class Barista {
	public void orderCoffee() {
		// enum can be accessed directly
		Coffee c0 = new Coffee(COFFEE_SIZE.LARGE, "milk coffee!!");
		System.out.println(c0.takeAway());

		// class name should be prefixed before enum is declared inside a class
		Coffee1 c1 = new Coffee1(Coffee1.COFFEE_SIZE.SMALL, "black coffee!!");
		System.out.println(c1.takeAway());
		c1.doSomething();

		// class name should be prefixed before enum is declared inside a class
		Coffee2 c2 = new Coffee2(Coffee2.COFFEE_SIZE.MEDIUM, "black coffee!!");
		System.out.println(c2.takeAway());

		System.out.println();
		for (COFFEE_SIZE cs : COFFEE_SIZE.values()) {
			System.out.print(cs + " ");
		}

	}
}
