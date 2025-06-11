// interfaces have no constructors
// abstract classes have constructors

class Boo extends Foo {
	// compiler will add a super no args in the constructor
	Boo() {
		System.out.println("Boo constructor!");
	}

	Boo(String name, int size) {
		this();

		// if explicit super with args is not called, it will call the default no args
		// constructor in the super class

		// super(name, size);
		System.out.println("Boo 2 args, constructor!");
		this.name = name;
		this.size = size;
	}

	private String name;
	private int size;
}
