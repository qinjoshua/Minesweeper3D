/**
 * Extends Cell, represents a mine cell
 * 
 * @author Nam
 *
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
	 * onClick - Explodes and return -1
	 * 
	 * @return - returns -1
	 */
	public int onClick() {
		explode();
		return -1;
	}

	/**
	 * explode - Displays explode animation
	 */
	public void explode() {
		// too be implemented later
	}
}
