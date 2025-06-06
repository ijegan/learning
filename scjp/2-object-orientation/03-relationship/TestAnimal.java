package com.example.models.relationship;

public class TestAnimal {

	public static void main(String[] args) {
		Animal a = new Animal();
		a.setLegs(4);
		healthCheck(a);

		Animal b = new Elephant();
		b.setLegs(4);
		healthCheck(b);

		// needed for Elephant, not visible in Animal, can be seen only in Elephant
		Rope r1 = new Rope();
		r1.setLength(4);
		r1.setMaterial("Metal");

		Elephant b1 = new Elephant();
		b1.setLegs(4);
		b1.setRope(r1);

		Animal ae = new AfricanElephant();
		healthCheck(ae);

		if (ae instanceof Animal) {
			System.out.println("elephant is of type animal");
		}

		if (a instanceof Elephant) {
			System.out.println("animal is of type elephant");
		} else {
			System.out.println("animal is not of type elephant");
		}

		// over riding example
		TestAnimal ta = new TestAnimal();

		Animal t1 = new Animal();
		Elephant t2 = new Elephant();

		Animal t3 = new Elephant();

		System.out.println("*** Over riding ***");
		// print animal
		ta.foo(t1);
		// print elephant
		ta.foo(t2);

		// elephant will not be printed
		// reference type determines which overloaded method to call is chosen at
		// compile time
		//
		// which version or object type to call decided at run time
		// output animal
		ta.foo(t3);

	}

	public static void healthCheck(Animal a) {
		a.walk();
	}

	void foo(Animal a) {
		System.out.println("Foo... Animal!!");
	}

	void foo(Elephant a) {
		System.out.println("Foo... Elephant!!");
	}
}
