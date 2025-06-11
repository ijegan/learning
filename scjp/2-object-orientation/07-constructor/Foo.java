class Foo {
	// there is no return type for a constructor
	Foo() {
		System.out.println("Foo constructor!");
	}

	Foo(String name, int size) {
		System.out.println("Foo 2args, constructor!");
		this.name = name;
		this.size = size;
	}

	private String name;
	private int size;

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public void display() {
		System.out.println("display method called in foo");
		System.out.println();
	}
}
