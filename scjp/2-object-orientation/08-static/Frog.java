class Frog {
	static int frogCount;

	Frog() {
		frogCount += 1;
	}

	void showCount() {
		System.out.println(frogCount);
	}

	public static void main(String[] args) {
		new Frog();
		new Frog();
		var f = new Frog();

		// cannot access non static methods in static context
		// this.showCount();

		f.showCount();

		// static variables can be accessed using dot operator
		System.out.println(Frog.frogCount);
	}
}
