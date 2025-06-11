class Hybrid implements Animal {
	public void makeSound() {
		System.out.println("make generic sound!!");
	}

	public Animal pup() {
		System.out.println("Hybrid pup!!");
		return new Hybrid();
	}
}
