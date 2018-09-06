package com.ie.robot.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ie.robot.model.Robot;

class RobotTest {

	private Robot robot;

	@BeforeEach
	void setUp() throws Exception {
		robot = new Robot();
	}

	@Test
	void testTurnLeft() {
		robot.place(2, 2, Robot.Direction.NORTH);
		robot.turnLeft();
		Assert.assertEquals(Robot.Direction.WEST, robot.getDirection());
		robot.turnLeft();
		Assert.assertEquals(Robot.Direction.SOUTH, robot.getDirection());
		robot.turnLeft();
		Assert.assertEquals(Robot.Direction.EAST, robot.getDirection());
		robot.turnLeft();
		Assert.assertEquals(Robot.Direction.NORTH, robot.getDirection());
	}

	@Test
	void testTurnRight() {
		robot.place(2, 2, Robot.Direction.NORTH);
		robot.turnRight();
		Assert.assertEquals(Robot.Direction.EAST, robot.getDirection());
		robot.turnRight();
		Assert.assertEquals(Robot.Direction.SOUTH, robot.getDirection());
		robot.turnRight();
		Assert.assertEquals(Robot.Direction.WEST, robot.getDirection());
		robot.turnRight();
		Assert.assertEquals(Robot.Direction.NORTH, robot.getDirection());
	}

	@Test
	void testMove_North() {
		robot.place(2, 2, Robot.Direction.NORTH);
		robot.move();
		// only Y should change
		Assert.assertEquals(2, robot.getX());
		Assert.assertEquals(3, robot.getY());
		Assert.assertEquals(Robot.Direction.NORTH, robot.getDirection());
	}

	@Test
	void testMove_East() {
		robot.place(2, 2, Robot.Direction.EAST);
		robot.move();
		// only X should change
		Assert.assertEquals(3, robot.getX());
		Assert.assertEquals(2, robot.getY());
		Assert.assertEquals(Robot.Direction.EAST, robot.getDirection());
	}

	@Test
	void testMove_South() {
		robot.place(2, 2, Robot.Direction.SOUTH);
		robot.move();
		// only Y should change
		Assert.assertEquals(2, robot.getX());
		Assert.assertEquals(1, robot.getY());
		Assert.assertEquals(Robot.Direction.SOUTH, robot.getDirection());
	}

	@Test
	void testMove_West() {
		robot.place(2, 2, Robot.Direction.WEST);
		robot.move();
		// only X should change
		Assert.assertEquals(1, robot.getX());
		Assert.assertEquals(2, robot.getY());
		Assert.assertEquals(Robot.Direction.WEST, robot.getDirection());
	}

	@Test
	void testPlace() {
		Assert.assertEquals("Placing with valid coordinates must be OK.", true,
				robot.place(2, 2, Robot.Direction.EAST));
		// coordinates updated
		Assert.assertEquals(2, robot.getX());
		Assert.assertEquals(2, robot.getY());
		Assert.assertEquals(Robot.Direction.EAST, robot.getDirection());

		// placing wrongly fails
		Assert.assertEquals("Placing null direction must fail.", false, robot.place(2, 2, null));

		// nothing should change
		Assert.assertEquals(2, robot.getX());
		Assert.assertEquals(2, robot.getY());
		Assert.assertEquals(Robot.Direction.EAST, robot.getDirection());
	}

}
