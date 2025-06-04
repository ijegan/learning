package com.example.models;

public class Coffee1 {

	// enum is declared inside class
	enum COFFEE_SIZE {
		SMALL, MEDIUM, LARGE
	}

	Coffee1(COFFEE_SIZE size, String drink) {
		this.size = size;
		this.drink = drink;
	}

	private COFFEE_SIZE size;
	private String drink;

	public String takeAway() {
		StringBuilder sb = new StringBuilder();
		sb.append(size);
		sb.append(" ");
		sb.append(drink);

		return sb.toString();
	}

	void doSomething() {
		enum PERSON {
			GOOD, BAD, UGLY
		}
		System.out.println(PERSON.GOOD);
	}

}
