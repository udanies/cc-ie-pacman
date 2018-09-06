package com.ie.robot.model;

/**
 * A robot model that can hold the current coordinates (0 indexed), the current
 * direction. The robot can be placed on a given coordinate using
 * {@link #place(int, int, Direction)} command. Position can be advanced using
 * {@link #move()}. The direction can be changed using {@link #turnLeft()} and
 * {@link #turnRight()}. {@link #report()} can be used to obtain the current
 * position and direction of the robot as a string.
 * 
 * @see Direction
 */
public class Robot {

	/**
	 * The supported directions by the robot - NORTH, EAST, SOUTH, OR WEST.
	 */
	public enum Direction {
	NORTH, EAST, SOUTH, WEST;

		/** The direction to the left of the current one */
		public Direction leftOf() {
			switch (this) {
			case NORTH:
				return WEST;
			case EAST:
				return NORTH;
			case SOUTH:
				return EAST;
			case WEST:
				return SOUTH;
			}
			return null; // should not happen
		}

		/** The direction to the right of the current one */
		public Direction rightOf() {
			switch (this) {
			case NORTH:
				return EAST;
			case EAST:
				return SOUTH;
			case SOUTH:
				return WEST;
			case WEST:
				return NORTH;
			}
			return null; // should not happen
		}
	}

	// current coordinates
	private int x;
	private int y;
	private Direction direction;

	/**
	 * create a robot that is at 0,0 facing NORTH
	 */
	public Robot() {
		this.x = 0;
		this.y = 0;
		this.direction = Direction.NORTH;
	}

	/**
	 * Create a new Robot at X, Y facing the direction
	 * 
	 * @param unitsX
	 * @param unitsY
	 */
	public Robot(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/**
	 * @return the current X coordinate (0 indexed)
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the current X coordinate
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the current Y coordinate (0 indexed)
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the current Y coordinate
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the current direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the current direction
	 * 
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Places the robot on the table at the given coordinates
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @return true if the placement was successful when all coordinates are valid,
	 *         false otherwise.
	 */
	public boolean place(int x, int y, Direction direction) {
		if (direction == null) {
			return false; // bad inputs
		}
		setX(x);
		setY(y);
		setDirection(direction);
		return true;
	}

	/**
	 * turns the direction to the left of the current one.
	 */
	public void turnLeft() {
		this.direction = this.direction.leftOf();
	}

	/**
	 * turns the direction to the left of the current one.
	 */
	public void turnRight() {
		this.direction = this.direction.rightOf();
	}

	/**
	 * moves one unit in the current direction.
	 * 
	 */
	public void move() {
		switch (direction) {
		case NORTH:
			y++;
			break;
		case EAST:
			x++;
			break;
		case SOUTH:
			y--;
			break;
		case WEST:
			x--;
			break;
		}
	}

	/**
	 * @return the current position and direction of the robot X,Y,Direction<br>
	 *         Example: "0,0,NORTH" or "2,3,EAST"
	 */
	public String report() {
		return String.format("%d,%d,%s", getX(), getY(), getDirection());
	}
}
