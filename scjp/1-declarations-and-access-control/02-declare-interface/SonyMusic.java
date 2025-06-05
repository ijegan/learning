package com.example.models;

class SonyMusic implements Sound {

	@Override
	public void playMusic() {
		System.out.println("playing music in sony player!!");
	}

	@Override
	public void changeSettings() {
		System.out.println("version: " + RECORD_VERSION);
		System.out.println("settings changed!!");
	}
}
