class Dog extends Animal implements Pet {
	void run() {
		System.out.println("Dog runs fast");
	}

	public void beFriendly(){
		System.out.println("Dog is friendly!");
	}
}

interface Pet {
	void beFriendly();
}
