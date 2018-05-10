import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Player class represents a player
 *
 *
 */
public class Player {
	private String name;
	private int score;
	private String time;

	/**
	 * Constructor that initializes the player name and score
	 * 
	 * @param n
	 *            - player name
	 * @param s
	 *            - player score;
	 */
	public Player(String n, int s) {
		name = n;
		score = s;
		LocalDateTime currentTime = LocalDateTime.now();
		time = currentTime.toString();
	}

	/**
	 * Gets player name
	 * 
	 * @return - returns the player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the player score
	 * 
	 * @return - returns the player score
	 */
	public int getScore() {
		return score;
	}

	public String getTime() {
		return time;
	}

	/**
	 * Compares this player to another and returns a number respective to it
	 * 
	 * @param other
	 *            - the other player that is to be compared to
	 * @return - return an int respective to the comparison
	 */
	public int compareTo(Player other) {
		if (this.getScore() > other.getScore()) {
			return 1;
		}

		if (this.getScore() < other.getScore()) {
			return -1;
		}
		return 0;
	}

	/**
	 * Returns the formatted string of name and score
	 * 
	 * @return - returns a string of name and score
	 */
	public String toString() {
		return String.format("%s %10s %10s", name, score, time);
	}
}
