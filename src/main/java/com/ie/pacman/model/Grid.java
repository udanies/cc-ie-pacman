package com.ie.pacman.model;

/**
 * A grid model than can hold its X and Y dimensions.
 * 
 * @see #isValidX(int)
 * @see #isValidY(int)
 * @see #isValidXY(int, int)
 *
 */
public class Grid {

	// Boundaries of the grid
	private int unitsX;
	private int unitsY;

	/**
	 * Create a new Grid with X and Y number of units to move between
	 * 
	 * @param unitsX
	 * @param unitsY
	 */
	public Grid(int unitsX, int unitsY) {
		this.unitsX = unitsX;
		this.unitsY = unitsY;
	}

	/**
	 * @return number of units on X axis
	 */
	public int getUnitsX() {
		return unitsX;
	}

	/**
	 * @return number of units on Y axis
	 */
	public int getUnitsY() {
		return unitsY;
	}

	/** checks if a given x coordinate is within the boundaries */
	public boolean isValidX(int x) {
		return (x >= 0 && x < unitsX);
	}

	/** checks if a given y coordinate is within the boundaries */
	public boolean isValidY(int y) {
		return (y >= 0 && y < unitsY);
	}

	/** checks if given x, y coordinates are within the boundaries */
	public boolean isValidXY(int x, int y) {
		return isValidX(x) && isValidY(y);
	}
}
