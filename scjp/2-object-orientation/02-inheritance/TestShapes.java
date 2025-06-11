class TestShapes {
	public static void main(String[] args) {
		PlayerPiece pp = new PlayerPiece();
		TilePiece tp = new TilePiece();

		doShape(pp);
		doShape(tp);

	}

	public static void doShape(GameShape gs) {
		gs.display();

		// movePiece is not visible since GameShape super class does not know
		// gs.movePiece();
	}
}
