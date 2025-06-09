public class AnimalTest {
	public static void main(String[] args) {
		// casting not needed for upcasting since it more restrictive
		Animal a = new Dog();
		a.makeNoise();

		// dog specific methods not visible of type animal
		// a.run();

		if (a instanceof Dog) {
			Dog d = (Dog) a;
			d.run();
		}

		Animal a1 = new Dog();

		// casting needed
		Dog d1 = (Dog) a1;
		d1.run();
		d1.beFriendly();

	}
}
