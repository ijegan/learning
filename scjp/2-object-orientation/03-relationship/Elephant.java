package com.example.models.relationship;

// Elephant is a Animal - 'extends' keyword
public class Elephant extends Animal {
	// Elephant has a relationship with Rope
	// Elephant has a reference to Rope
	private Rope rope;

	public void walk() {
		System.out.println("elephant is walking!!");
	}

	public Rope getRope() {
		return rope;
	}

	public void setRope(Rope rope) {
		this.rope = rope;
	}
}
