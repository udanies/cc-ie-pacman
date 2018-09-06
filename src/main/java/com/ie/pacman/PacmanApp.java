package com.ie.robot;

import com.ie.robot.engine.RobotEngine;

public class ToyRobot {

	/**
	 * Runs the robot simulation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RobotEngine.getInstance().run();
		System.exit(0);
	}

}
