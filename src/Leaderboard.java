import java.lang.Object;

import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Leaderboard class handles the adding, sorting, and returning of the
 * leaderboard
 *
 */
public class Leaderboard {
	ArrayList<Player> board;// contains all players

	/**
	 * Constructor that initializes board
	 */
	public Leaderboard() {
		board = new ArrayList<Player>();
	}

	/**
	 * Adds a player to the board
	 * 
	 * @param p
	 *            - takes in a player object
	 */
	public void addPlayer(Player p) {
		board.add(p);
	}

	/**
	 * A helper method that organizes the list of players for later use
	 * 
	 * @param list
	 *            - takes in an ArrayList that contains all of the data
	 * @param first
	 *            - takes in the first position
	 * @param last
	 *            - takes in the last position
	 */
	private void quickSort(ArrayList<Player> list, int first, int last) {
		int g = first, h = last;
		int midIndex = (first + last) / 2;
		Player dividingValue = list.get(midIndex);
		do {
			while (list.get(g).compareTo(dividingValue) < 0)
				g++;
			while (list.get(h).compareTo(dividingValue) > 0)
				h--;
			if (g <= h) {
				// swap(list[g], list[h]);
				swap(list, g, h);
				g++;
				h--;
			}
		} while (g < h);
		if (h > first)
			quickSort(list, first, h);
		if (g < last)
			quickSort(list, g, last);
	}

	/**
	 * A method that is to only used by Quicksort for sorting purposes
	 * 
	 * @param list
	 *            - takes in the list that needs to be changed
	 * @param g
	 *            - takes in the first thing that needs to be swapped with h
	 * @param h
	 *            - the second object that is taken in to swap with g
	 */
	private void swap(ArrayList<Player> list, int g, int h) {
		Player temp = list.get(g);
		list.set(g, list.get(h));
		list.set(h, temp);
	}

	/**
	 * Returns an arraylist of the top x amount of players
	 * 
	 * @param range
	 *            - shows the top player sup to x (inclusive)
	 * @return - returns an arraylist of top x players
	 */
	public ArrayList topXPlayers(int range) {
		ArrayList<Player> topx = new ArrayList<Player>();
		int difference = 0;
		boolean isGreaterthan = false;
		quickSort(board, 0, board.size() - 1);

		for (int k = 0; k < range && range <= board.size(); k++) {
			topx.add(board.get(k));
		}

		if (range > board.size()) {
			difference = range - board.size();
			isGreaterthan = true;
			for (int i = 0; i < difference; i++) {
				topx.add(null);
			}
		}
		return topx;
	}

	/**
	 * A simple method that gets the usual top 5 players
	 * 
	 * @return - returns an arraylist of the top 5 players
	 */
	public ArrayList getTop5() {
		return topXPlayers(5);
	}

	/**
	 * Method displays the board for within the x range
	 * 
	 * @param range
	 *            - the range at which the board should display information
	 */
	public void displayBoard(int range) {
		ArrayList<Player> temp = topXPlayers(range);
		for (int k = 1; k <= temp.size(); k++) {
			System.out.println(k + ") " + temp.get(k).toString());
		}
	}

	/**
	 * The file writer method that creates a txt file that stores all of the
	 * information of the player
	 */
	public void txtFile() {
		try {
			boolean isFileNeeded = true;
			File file;
			ArrayList<Player> temp = getTop5();

			do {
				file = new File("scores.txt");
				isFileNeeded = false;
			} while (isFileNeeded == true);

			if (file.createNewFile() == true) {
				FileWriter out = new FileWriter(file);
				for (int k = 0; k < temp.size(); k++) {
					int rank = k + 1;
					String s = rank + ") " + temp.get(0).toString() + "\n";
					out.write(s, 0, s.length());
					out.close();
				}
			} else {
				System.out.println("ERROR: txtfile() method");
			}
		} catch (Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
	}

}
