package com.example.models.relationship;

public class Animal {
	private int legs;
	private int weight;
	private String continent;

	public void walk() {
		System.out.println("animal is walking!!");
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
}
