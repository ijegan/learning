package com.example.models;

public class Coffee2 {

	// enum is declared inside class
	enum COFFEE_SIZE {
		// enum can have private variables and a constructor
		SMALL(1), MEDIUM(5), LARGE(10);

		COFFEE_SIZE(int ounces) {
			this.ounces = ounces;
		}

		private int ounces;
	}

	Coffee2(COFFEE_SIZE size, String drink) {
		this.size = size;
		this.drink = drink;
	}

	private COFFEE_SIZE size;
	private String drink;

	// enum can be accessed directly
	public String takeAway() {
		StringBuilder sb = new StringBuilder();
		sb.append(size);
		sb.append(" ");
		sb.append(drink);
		sb.append(" ");
		sb.append(size.ounces);
		sb.append(" ounces");

		return sb.toString();
	}

	void doSomething() {
		// enum cannot be declared inside a method ??
		enum PERSON {
			GOOD, BAD, UGLY
		}
		System.out.println(PERSON.GOOD);
	}

}
