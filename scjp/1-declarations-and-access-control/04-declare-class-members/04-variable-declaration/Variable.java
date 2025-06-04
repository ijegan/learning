package com.example.models;

public class Variable {
	// serialization - writing the state of the object to a special type of I/O
	// stream
	// an object can be saved to a file and deserialized
	// jvm will skip this variable when serialized
	// transient keyword is applicable only to instance variables
	private transient String label;

	// volatile - when multiple threads are trying to access instance variable in a
	// multi threaded environment, synchronized keyword for methods is preferred

	// static keyword refers the class
	private static String tag;

	Variable() {

	}

	Variable(String label) {
		this.label = label;
	}

	public void doSomething() {
		// illegal modifier only final is permitted
		// transient String str1;

		// static variables cannot be accessed by dot operator
		Variable v1 = new Variable();

		// Invalid
		// v1.tag = "ID01";

		// valid
		Variable.tag = "ID01";
		
		System.out.print(v1.getTag());
	}

	public String getString() {
		return this.label;
	}

	public void setString(String label) {
		this.label = label;
	}

	public String getTag() {
		return tag;
	}
}
