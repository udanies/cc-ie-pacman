package com.ie.pacman;

import com.ie.pacman.engine.PacmanEngine;

public class PacmanApp {

	/**
	 * Runs the Pacman simulation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PacmanEngine.getInstance().run();
		System.exit(0);
	}

}
