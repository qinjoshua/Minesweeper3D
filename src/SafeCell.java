/**
 * Extends cell, represents a safecell
 * 
 * @author Nam
 *
 */
public class SafeCell extends Cell {
	/**
	 * Constructor that calls on super
	 * 
	 * @param s
	 *            - state
	 * @param m
	 *            - mine
	 */
	public SafeCell(int s, boolean m) {
		super(s, m);
	}

	/**
	 * onClick - Displays the number of miens around it, returns the number of mines
	 * around
	 */
	public int onClick() {
		int mineCount = 0;
		// too be implemented later
		return mineCount;
	}

}
