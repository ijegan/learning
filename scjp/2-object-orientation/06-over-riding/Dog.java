class Dog implements Animal {
	public void makeSound() {
		System.out.println("Bark!!");
	}

	public Dog pup() {
		System.out.println("Dog!!");
		return new Dog();
	}

	// object return type can accept null
	public Dog run() {
		return null;
	}

	// return type can accept arrays
	public Dog[] findAll() {
		Dog[] dogs = { new Dog(), new Dog() };
		return dogs;
	}

	
	public int findDogIndex(){
		char c = 'd';
		return c;
	}

	public int findCageNumber(){
		float cage = 3.14f;
		return (int)cage;
	}
}
