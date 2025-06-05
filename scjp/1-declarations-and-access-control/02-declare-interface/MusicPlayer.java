package com.example.models;

class MusicPlayer {
	void display() {
		AppleMusic am = new AppleMusic();
		am.playMusic();
		am.changeSettings();

		SonyMusic sm = new SonyMusic();
		sm.playMusic();
		sm.changeSettings();
	}
}
