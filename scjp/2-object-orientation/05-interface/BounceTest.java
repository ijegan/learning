class BounceTest {
	public static void main(String[] args){
		BeachBall b = new BeachBall();
		b.bounce();
	}
}

abstract class Ball implements Movable, Bouncable {
	
}

class BeachBall extends Ball {
	public void bounce(){
		System.out.println("bounce!!");
	}

	public void updateBounceFactor(){

	}

	public void moveIt(){

	}
}

interface Movable {
	void moveIt();
}

interface Bouncable {
	void bounce();
	void updateBounceFactor();
}
