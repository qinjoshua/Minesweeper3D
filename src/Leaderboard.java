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
	private void quickSort(ArrayList<Player> A, int p, int r) {
		if (p < r) {
			int q = sort(A, p, r);
			quickSort(A, p, q);
			quickSort(A, q + 1, r);
		}
	}

	/**
	 * Helper class for quicksort
	 * 
	 * @param A
	 *            - takes in the arraylist that is to be sorted
	 * @param p
	 *            - takes in the beginning
	 * @param r
	 *            - takes in the end
	 * @return - returns int
	 */
	private int sort(ArrayList<Player> A, int p, int r) {
		int x = A.get(p).getScore(); // pivot
		int i = p;
		int j = r;
		while (true) {

			while (A.get(i).getScore() > x) {
				i++;
			}

			while (A.get(j).getScore() < x) {
				j--;
			}
			if (i < j) {
				Player temp = A.get(i);
				A.set(i, A.get(j));
				A.set(j, temp);
			} else {
				return j;
			}
		}
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
		try {
			System.out.printf("%-10s %7s %18s", "Player Name", "Score", "Time Achieved\n");
			ArrayList<Player> temp = topXPlayers(range);
			for (int k = 0; k <= temp.size(); k++) {
				int rank = k + 1;
				System.out.println(rank + ") " + temp.get(k).toString());
			}
		} catch (Exception e) {
			// System.out.print("Error:" + e.getMessage() );
		}
	}

	/**
	 * The file writer method that creates a txt file that stores all of the
	 * information of the player
	 */
	public void txtFile() {
		FileWriter out = null;
		try {

			ArrayList<Player> temp = getTop5();

			out = new FileWriter("src/scores.txt");
			out.write(String.format("%8s %10s %12s", "Name", "Score", "Time\n"));
			out.flush();
			for (int k = 0; k < temp.size(); k++) {
				String s = "";
				int rank = k + 1;
				s = rank + ") " + temp.get(k).toString() + "\n";
				out.write(s);
				out.flush();
				System.out.println("File written");
			}
			out.close();

		} catch (Exception e) {
			System.out.print("Error: " + e.getMessage());
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				System.out.print("Close error");
			}
		}
	}

}
