package com.example.models.inheritance;

class PlayerPiece extends GameShape implements Animate {
	public void movePiece() {
		System.out.println("moving piece");
	}

	public void animation() {
		System.out.println("animating player piece");
	}


	public void display() {
		System.out.println("displaying shape in Player Piece!");
	}
}
