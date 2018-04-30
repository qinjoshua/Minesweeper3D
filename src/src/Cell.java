/**
 * Abstract class for all cells
 * 
 * @author Nam
 *
 */
public abstract class Cell {
	private boolean clickable;
	private int state;
	private boolean mine;

	/**
	 * Constructor that initializes state, mine, and clickable
	 * 
	 * @param s
	 *            - initialize state
	 * @param m
	 *            - initialize mine
	 */
	public Cell(int s, boolean m) {
		state = s;
		mine = m;
		clickable = true;
	}

	/**
	 * Turns off clickable
	 */
	public void Flagged() {
		clickable = false;
	}

	/**
	 * Displays question mark
	 */
	public void Question() {
		// replace with mehtod of displaying questionmark
		System.out.print("?");
	}

	/**
	 * Abstract method
	 * 
	 * @return - int
	 */
	public abstract int OnClick();

	/**
	 * Getter method for Clickable
	 * 
	 * @return - clickable
	 */
	public boolean getClickable() {
		return clickable;
	}

	/**
	 * Getter method for Mine
	 * 
	 * @return - mine
	 */
	public boolean getMine() {
		return mine;
	}

	/**
	 * Getter method for State
	 * 
	 * @return - state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Setter method for Clickable
	 * 
	 * @param c
	 *            - clickable
	 */
	public void setClickable(boolean c) {
		clickable = c;
	}

	/**
	 * Setter method for Mine
	 * 
	 * @param m
	 *            - Mine
	 */
	public void setMine(boolean m) {
		mine = m;
	}

	/**
	 * Setter method for State
	 * 
	 * @param s
	 *            - State
	 */
	public void setState(int s) {
		state = s;
	}
}
