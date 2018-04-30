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
	 * Explodes and return -1
	 * 
	 * @return - returns -1
	 */
	public int OnClick() {
		Explode();
		return -1;
	}

	/**
	 * Displays explode animation
	 */
	public void Explode() {
		// too be implemented later
	}
}
