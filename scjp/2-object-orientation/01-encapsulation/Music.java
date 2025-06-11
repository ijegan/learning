enum level {
	LOW, MEDIUM, HIGH
}

public class Music {
	// before 
	// public int volume;
	
	// after, can break functionality
	private int volume;

	void playMusic() {
		System.out.println("Playing music at volume: " + volume);
	}

	// get & set added later
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
