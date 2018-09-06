package com.ie.pacman.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ie.pacman.model.Pacman;

class PacmanTest {

	private Pacman pacman;

	@BeforeEach
	void setUp() throws Exception {
		pacman = new Pacman();
	}

	@Test
	void testTurnLeft() {
		pacman.place(2, 2, Pacman.Direction.NORTH);
		pacman.turnLeft();
		Assert.assertEquals(Pacman.Direction.WEST, pacman.getDirection());
		pacman.turnLeft();
		Assert.assertEquals(Pacman.Direction.SOUTH, pacman.getDirection());
		pacman.turnLeft();
		Assert.assertEquals(Pacman.Direction.EAST, pacman.getDirection());
		pacman.turnLeft();
		Assert.assertEquals(Pacman.Direction.NORTH, pacman.getDirection());
	}

	@Test
	void testTurnRight() {
		pacman.place(2, 2, Pacman.Direction.NORTH);
		pacman.turnRight();
		Assert.assertEquals(Pacman.Direction.EAST, pacman.getDirection());
		pacman.turnRight();
		Assert.assertEquals(Pacman.Direction.SOUTH, pacman.getDirection());
		pacman.turnRight();
		Assert.assertEquals(Pacman.Direction.WEST, pacman.getDirection());
		pacman.turnRight();
		Assert.assertEquals(Pacman.Direction.NORTH, pacman.getDirection());
	}

	@Test
	void testMove_North() {
		pacman.place(2, 2, Pacman.Direction.NORTH);
		pacman.move();
		// only Y should change
		Assert.assertEquals(2, pacman.getX());
		Assert.assertEquals(3, pacman.getY());
		Assert.assertEquals(Pacman.Direction.NORTH, pacman.getDirection());
	}

	@Test
	void testMove_East() {
		pacman.place(2, 2, Pacman.Direction.EAST);
		pacman.move();
		// only X should change
		Assert.assertEquals(3, pacman.getX());
		Assert.assertEquals(2, pacman.getY());
		Assert.assertEquals(Pacman.Direction.EAST, pacman.getDirection());
	}

	@Test
	void testMove_South() {
		pacman.place(2, 2, Pacman.Direction.SOUTH);
		pacman.move();
		// only Y should change
		Assert.assertEquals(2, pacman.getX());
		Assert.assertEquals(1, pacman.getY());
		Assert.assertEquals(Pacman.Direction.SOUTH, pacman.getDirection());
	}

	@Test
	void testMove_West() {
		pacman.place(2, 2, Pacman.Direction.WEST);
		pacman.move();
		// only X should change
		Assert.assertEquals(1, pacman.getX());
		Assert.assertEquals(2, pacman.getY());
		Assert.assertEquals(Pacman.Direction.WEST, pacman.getDirection());
	}

	@Test
	void testPlace() {
		Assert.assertEquals("Placing with valid coordinates must be OK.", true,
				pacman.place(2, 2, Pacman.Direction.EAST));
		// coordinates updated
		Assert.assertEquals(2, pacman.getX());
		Assert.assertEquals(2, pacman.getY());
		Assert.assertEquals(Pacman.Direction.EAST, pacman.getDirection());

		// placing wrongly fails
		Assert.assertEquals("Placing null direction must fail.", false, pacman.place(2, 2, null));

		// nothing should change
		Assert.assertEquals(2, pacman.getX());
		Assert.assertEquals(2, pacman.getY());
		Assert.assertEquals(Pacman.Direction.EAST, pacman.getDirection());
	}

}
