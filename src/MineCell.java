/**MineCell class that represents a cell with a mine in it
 * Extends cell, and is used in the board[][][] array in the Board Class
 */
public class MineCell extends Cell {
	/**
	 * Constructor calls on super
	 * 
	 * @param s
	 *            - state
	 * @param m
	 *            - mine
	 */
	public MineCell(int s, boolean m) {
		super(s, m);
	}

	/**
	 * onClick method is called upon a user click
	 * The state is set to -1 as there clicking on a minecell ends the game
	 * @return - returns -1
	 */
	public int onClick() {
		explode();
		return -1;
	}

	/**
	 * explode - Displays explode animation
	 * Moved to the MinesUI class
	 */
	public void explode() {
		//explode() has been moved to the MinesUI class
	}
}
