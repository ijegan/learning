public class WalkMan {
	// void playMusic(){
	public static void main(String[] args) { 
		Music m = new Music();
		// before	
		// m.volume = 10;
		
		// after, volume can be set by setter methods only
		m.setVolume(10);
		m.playMusic();
	}
}
