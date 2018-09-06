package com.ie.pacman.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ie.pacman.engine.PacmanEngine;
import com.ie.pacman.model.Pacman;

class PacmanEngineTest {

	PacmanEngine engine = PacmanEngine.getInstance();

	@BeforeEach
	void setUp() throws Exception {
		engine.reset();
	}

	@Test
	void testProcessInstruction_Null() {
		assertEquals(null, engine.processInstruction(null));
	}

	@Test
	void testProcessInstruction_Quit() {
		assertEquals(null, engine.processInstruction("QUIT"));
	}

	@Test
	void testProcessInstruction_Empty() {
		assertEquals("", engine.processInstruction(""));
	}

	@Test
	void testProcessInstruction_NotQuit() {
		assertEquals("", engine.processInstruction("BLAH"));
	}

	@Test
	void test_Commands_Moves_Without_Place() {
		String[] instructions = { "LEFT", "RIGHT", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("", actual);
	}

	@Test
	void test_Commands_Place_Then_Moves() {
		String[] instructions = { "PLACE 1,1,EAST", "LEFT", "RIGHT", "MOVE", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("2,1,EAST", actual);
	}

	@Test
	void test_Commands_Moves_Then_Place() {
		String[] instructions = { "LEFT", "PLACE 1,1,WEST", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("1,1,WEST", actual);
	}

	@Test
	void test_Commands_Place_Twice() {
		String[] instructions = { "PLACE 1,1,EAST", "MOVE", "PLACE 3,3,WEST", "MOVE", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("2,3,WEST", actual);
	}

	@Test
	void test_Commands_Place_BadParams() {
		String[] instructions = { "PLACE 9,9,EAST", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("", actual);
	}

	@Test
	void test_Commands_Place_GoodParams_Then_BadParams() {
		String[] instructions = { "PLACE 1,1,EAST", "MOVE", "PLACE 9,9,NORTH", "MOVE", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("3,1,EAST", actual);
	}

	@Test
	void test_Commands_CaseSensitivity() {
		String[] instructions = { "place 1,1,east", "move", "Report" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("2,1,EAST", actual);
	}

	@Test
	void test_Commands_SpacesInCommand() {
		String[] instructions = { " place  1 , 1 ,  east  ", " MOVE ", " REPORT " };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("2,1,EAST", actual);
	}

	@Test
	void test_Commands_HittingTheBoundry() {
		String[] instructions = { "PLACE 1,1,EAST", "RIGHT", "MOVE", "MOVE", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("1,0,SOUTH", actual);
	}

	@Test
	void test_Commands_HittingTheCorner() {
		String[] instructions = { "PLACE 1,1,EAST", "RIGHT", "MOVE", "MOVE", "RIGHT", "MOVE", "MOVE", "REPORT" };
		String actual = engine.processInstructions(instructions);
		Assert.assertEquals("0,0,WEST", actual);
	}

	@Test
	void testMove_North() {
		engine.place(2, 2, Pacman.Direction.NORTH);
		Assert.assertEquals(true, engine.move());
		// only Y should change
		Assert.assertEquals("2,3,NORTH", engine.report());

		Assert.assertEquals(true, engine.move());
		Assert.assertEquals("2,4,NORTH", engine.report());

		// cannot move anymore
		Assert.assertEquals(false, engine.move());
		Assert.assertEquals("2,4,NORTH", engine.report());
	}

	@Test
	void testMove_East() {
		engine.place(2, 2, Pacman.Direction.EAST);
		Assert.assertEquals(true, engine.move());
		// only X should change
		Assert.assertEquals("3,2,EAST", engine.report());

		Assert.assertEquals(true, engine.move());
		Assert.assertEquals("4,2,EAST", engine.report());

		// cannot move anymore
		Assert.assertEquals(false, engine.move());
		Assert.assertEquals("4,2,EAST", engine.report());
	}

	@Test
	void testMove_South() {
		engine.place(2, 2, Pacman.Direction.SOUTH);
		Assert.assertEquals(true, engine.move());
		// only Y should change
		Assert.assertEquals("2,1,SOUTH", engine.report());

		Assert.assertEquals(true, engine.move());
		Assert.assertEquals("2,0,SOUTH", engine.report());

		// cannot move anymore
		Assert.assertEquals(false, engine.move());
		Assert.assertEquals("2,0,SOUTH", engine.report());

	}

	@Test
	void testMove_West() {
		engine.place(2, 2, Pacman.Direction.WEST);
		Assert.assertEquals(true, engine.move());
		// only X should change
		Assert.assertEquals("1,2,WEST", engine.report());

		Assert.assertEquals(true, engine.move());
		Assert.assertEquals("0,2,WEST", engine.report());

		// cannot move anymore
		Assert.assertEquals(false, engine.move());
		Assert.assertEquals("0,2,WEST", engine.report());
	}

	@Test
	void testPlace() {
		// placing wrongly fails
		Assert.assertEquals("Placing X, Y too big must fail.", false, engine.place(9, 9, Pacman.Direction.NORTH));
		Assert.assertEquals("Placing Y too big must fail.", false, engine.place(0, 9, Pacman.Direction.NORTH));
		Assert.assertEquals("Placing X too big must fail.", false, engine.place(9, 0, Pacman.Direction.NORTH));
		Assert.assertEquals("Placing X, Y too small must fail.", false, engine.place(-1, -1, Pacman.Direction.NORTH));
		Assert.assertEquals("Placing X too small must fail.", false, engine.place(-1, 2, Pacman.Direction.NORTH));
		Assert.assertEquals("Placing Y too small must fail.", false, engine.place(2, -1, Pacman.Direction.NORTH));
		Assert.assertEquals("Placing unknown direction must fail.", false, engine.place(2, 2, "blah"));

		// placing with known directions must be OK
		Assert.assertEquals("Placing with known directions must be OK.", true, engine.place(0, 0, "NORTH"));
		Assert.assertEquals("Placing with known directions must be OK.", true, engine.place(0, 0, "EAST "));
		Assert.assertEquals("Placing with known directions must be OK.", true, engine.place(0, 0, " SOUTH"));
		Assert.assertEquals("Placing with known directions must be OK.", true, engine.place(0, 0, "west"));

		// placing with valid coordinates must be OK
		Assert.assertEquals("Placing with valid coordinates must be OK.", true,
				engine.place(2, 2, Pacman.Direction.EAST));
	}

	@Test
	void testPlaceAndMove() {
		// move without placing fails
		Assert.assertEquals("Move without placing must fail.", false, engine.move());
		// place in a good position
		engine.place(2, 2, Pacman.Direction.NORTH);
		// move must be OK
		Assert.assertEquals("Move after placing must be OK.", true, engine.move());
		Assert.assertEquals("2,3,NORTH", engine.report());
	}
}
