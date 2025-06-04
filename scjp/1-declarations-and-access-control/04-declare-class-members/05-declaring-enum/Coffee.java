package com.example.models;

// enum is declared outside class
enum COFFEE_SIZE {
	SMALL, MEDIUM, LARGE
}

public class Coffee {

	Coffee(COFFEE_SIZE size, String drink) {
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

}
