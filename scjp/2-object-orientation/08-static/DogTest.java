class DogTest {
	public static void main(String[] args) {
		Animal.makeSound();
		Dog.makeSound();

		Dog d1 = new Dog();
		
		Animal a1 = new Animal();
		Animal a2 = new Dog();

		System.out.println();

		d1.makeSound();
		a1.makeSound();
		a2.makeSound();
	}
}
