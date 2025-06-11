class GermanShepherd extends Dog {

	public Dog pup() {
		System.out.println("German Shepherd!");
		return new GermanShepherd();
	}

	// override return type can only be subtype
	/*
	 * public Cat pup() {
	 * System.out.println("Cat!!");
	 * return new Cat();
	 * }
	 */
}
