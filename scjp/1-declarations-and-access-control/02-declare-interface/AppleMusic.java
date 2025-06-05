package com.example.models;

public class AppleMusic implements Sound {

	@Override
	public void playMusic() {
		System.out.println("playing music in apple ipod!!");
	}

	@Override
	public void changeSettings() {
		System.out.println("version: " + RECORD_VERSION);
		System.out.println("settings changed in apple ipod!!");
	}
}
