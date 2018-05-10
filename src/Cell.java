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
	private boolean flipped;
	
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
		flipped = false;
	}

	/**
	 * flagged - Turns off clickable
	 */
	public void flagged() {
		clickable = false;
	}
	
	public boolean getFlipped()
	{
		return flipped;
	}

	/**
	 * Question - Displays question mark
	 */
	public void Question() {
		// replace with mehtod of displaying questionmark
		System.out.print("?");
	}

	/**
	 * onClick - Abstract method
	 * 
	 * @return - int
	 */
	public abstract int onClick();

	/**
	 * getClickable - Getter method for Clickable
	 * 
	 * @return - clickable
	 */
	public boolean getClickable() {
		return clickable;
	}

	/**
	 * getMine - Getter method for Mine
	 * 
	 * @return - mine
	 */
	public boolean getMine() {
		return mine;
	}

	/**
	 * getState - Getter method for State
	 * 
	 * @return - state
	 */
	public int getState() {
		return state;
	}

	/**
	 * setClickable - Setter method for Clickable
	 * 
	 * @param c
	 *            - clickable
	 */
	public void setClickable(boolean c) {
		clickable = c;
	}

	/**
	 * setMine - Setter method for Mine
	 * 
	 * @param m
	 *            - Mine
	 */
	public void setMine(boolean m) {
		mine = m;
	}

	/**
	 * setState - Setter method for State
	 * 
	 * @param s
	 *            - State
	 */
	public void setState(int s) {
		state = s;
	}
}
