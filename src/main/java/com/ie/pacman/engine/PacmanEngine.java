package com.ie.pacman.engine;

import java.io.Console;
import java.io.IOError;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ie.pacman.model.Pacman;
import com.ie.pacman.model.Grid;
import com.ie.pacman.model.Pacman.Direction;

/**
 * The Pacman engine that simulates Pacman moving on a grid. <br>
 * Supported commands within the dimensions of the 5x5 grid:
 * <ul>
 * <li>PLACE X,Y,F - places Pacman at the given X,Y coordinates facing NORTH,
 * EAST, SOUTH, or WEST.<br>
 * E.g.: PLACE 2,3,EAST
 * <li>MOVE - moves one position in the current direction
 * <li>LEFT - turns 90 degrees to the left from the current direction
 * <li>RIGHT - turns 90 degrees to the right from the current direction
 * <li>REPORT - displays the current position and direction
 * <li>QUIT - to exit the simulation
 * </ul>
 * Notes:
 * <ul>
 * <li>Coordinates 0,0 is the SOUTH WEST corner of the grid.
 * <li>All commands before correctly placing Pacman are ignored.
 * <li>All malformed commands are ignored and does not affect the current
 * position of Pacman.
 * <li>Commands are not case sensitive.
 * </ul>
 * 
 * @see #getInstance() to get the engine instance
 * @see #run() to start the simulation
 * @see #reset() to reset the grid
 */
public class PacmanEngine {

	private static final String CONSOLE_MSG_EXIT = "Simulation ended. Goodbye!\r\n";
	private static final String CONSOLE_MSG_INSTRUCTION = "Please enter your commands or 'quit' to end the simulation.\r\n";
	private static final String CONSOLE_MSG_WELCOME = "Welcome to the Pacman simulation!\r\n";
	private static final String CONSOLE_PROMPT = "> ";
	private static final String CMD_QUIT = "QUIT";
	private static final String CMD_REPORT = "REPORT";
	private static final String CMD_LEFT = "LEFT";
	private static final String CMD_RIGHT = "RIGHT";
	private static final String CMD_MOVE = "MOVE";
	private static final String CMD_PLACE_REGEX = "^\\s*PLACE\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(NORTH|EAST|SOUTH|WEST)\\s*$";
	private static final Pattern CMD_PLACE_PATTERN = Pattern.compile(CMD_PLACE_REGEX, Pattern.CASE_INSENSITIVE);

	private static PacmanEngine theEngine;

	private static Console console = System.console();
	private Pacman thePacman;
	private Grid theGrid;

	static {
		theEngine = new PacmanEngine();
	}

	private PacmanEngine() {
		theGrid = new Grid(5, 5);
		reset(); // initialise
	}

	/**
	 * @return the {@link PacmanEngine} instance
	 */
	public static PacmanEngine getInstance() {
		return theEngine;
	}

	/**
	 * Introduces the simulation and listens to the user input commands.
	 */
	public void run() {
		console.printf(CONSOLE_MSG_WELCOME);
		console.printf(CONSOLE_MSG_INSTRUCTION);

		try {
			String instr = null;
			String result = null;
			do {
				// keep reading lines
				instr = console.readLine(CONSOLE_PROMPT);
				result = processInstruction(instr);
				if (result != null && !result.trim().isEmpty()) {
					// display if any info was returned
					console.printf(CONSOLE_PROMPT + result);
				}
			} while (result != null);
		} catch (IOError e) {
			System.err.println(e.getMessage());
		}

		console.printf(CONSOLE_MSG_EXIT);
	}

	/**
	 * reset the grid, with no Pacman placed yet
	 */
	public void reset() {
		thePacman = null;
	}

	/**
	 * Process an instruction and return the result.
	 * 
	 * @param cmd line of instruction
	 * @return result of the command or null if no more commands are to be accepted
	 */
	public String processInstruction(String cmd) {
		if (cmd == null || cmd.equalsIgnoreCase(CMD_QUIT)) {
			return null;
		}

		// handle place
		Matcher m = CMD_PLACE_PATTERN.matcher(cmd);
		if (m.matches()) {
			place(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), m.group(3));
			return "";
		}

		// handle known moves
		switch (cmd.trim().toUpperCase()) {
		case CMD_MOVE:
			move();
			break;
		case CMD_RIGHT:
			turnRight();
			break;
		case CMD_LEFT:
			turnLeft();
			break;
		case CMD_REPORT:
			return String.format("%s\n", report());
		}

		return "";
	}

	/**
	 * Process a set of instructions and return the results
	 * 
	 * @param instructions
	 * @return the results of executing the given instructions, or an empty string
	 *         if no valid report commands were issued.
	 */
	public String processInstructions(String[] instructions) {
		StringBuffer results = new StringBuffer();
		String result = "";
		for (String string : instructions) {
			result = processInstruction(string);
			if (result != null) {
				results.append(result);
			}
		}
		return results.toString().trim();
	}

	/**
	 * Places Pacman on the grid at the given coordinates
	 * 
	 * @param x
	 * @param y
	 * @param direction as a String
	 * @return true if the placement was successful when all coordinates are valid,
	 *         false otherwise.
	 * @see #place(int, int, Direction)
	 */
	public boolean place(int x, int y, String direction) {
		try {
			Direction d = Direction.valueOf(direction.trim().toUpperCase());
			return place(x, y, d);
		} catch (NullPointerException | IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Places Pacman on the grid at the given coordinates
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @return true if the placement was successful when all coordinates are valid,
	 *         false otherwise.
	 */
	public boolean place(int x, int y, Direction direction) {
		if (direction == null || !theGrid.isValidXY(x, y)) {
			return false; // bad inputs
		}
		if (this.thePacman == null) {
			this.thePacman = new Pacman(x, y, direction);
		} else {
			this.thePacman.place(x, y, direction);
		}
		return true;
	}

	/**
	 * @return whether Pacman is currently placed on valid coordinate
	 */
	public boolean isPlaced() {
		return this.thePacman != null;
	}

	/**
	 * if Pacman is placed on a grid, turns the direction to the left of the
	 * current one, does nothing otherwise.
	 */
	public void turnLeft() {
		if (isPlaced()) {
			this.thePacman.turnLeft();
		}
	}

	/**
	 * if Pacman is placed on a grid, turns the direction to the left of the
	 * current one, does nothing otherwise.
	 */
	public void turnRight() {
		if (isPlaced()) {
			this.thePacman.turnRight();
		}
	}

	/**
	 * moves one unit in the current direction if it is within the bounds.
	 * 
	 * @return true if the move was successful, false otherwise
	 */
	public boolean move() {
		if (!canMove()) {
			return false;
		}
		this.thePacman.move();
		return true;
	}

	private boolean canMove() {
		if (!isPlaced()) {
			return false;
		}
		switch (thePacman.getDirection()) {
		case NORTH:
			return theGrid.isValidY(thePacman.getY() + 1);
		case EAST:
			return theGrid.isValidX(thePacman.getX() + 1);
		case SOUTH:
			return theGrid.isValidY(thePacman.getY() - 1);
		case WEST:
			return theGrid.isValidX(thePacman.getX() - 1);
		}
		return false;
	}

	/**
	 * @return the current position and direction of Pacman X,Y,Direction<br>
	 *         Example: "0,0,NORTH" or "2,3,EAST" or empty string "" if Pacman is
	 *         not yet placed.
	 */
	public String report() {
		if (isPlaced()) {
			return thePacman.report();
		}
		return "";
	}
}
