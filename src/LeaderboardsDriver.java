
public class LeaderboardsDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Leaderboard board = new Leaderboard();
		board.addPlayer(new Player("Highest", 2000, 10));
		board.addPlayer(new Player("Score", 2, 10));
		board.addPlayer(new Player("This", 9000, 9));
		board.addPlayer(new Player("is", 2900, 19));
		board.addPlayer(new Player("the", 2090, 100));
		board.addPlayer(new Player("Treees", 91000, 9));
		board.txtFile();
		// board.displayBoard(5);
	}

}
