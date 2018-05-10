
public class LeaderboardsDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Leaderboard board = new Leaderboard();
		board.addPlayer(new Player("Jeff", 2000));
		board.addPlayer(new Player("ieff", 2));
		board.addPlayer(new Player("peff", 9000));
		board.addPlayer(new Player("ueff", 2900));
		board.addPlayer(new Player("keff", 2090));
		//board.txtFile();
		board.displayBoard(5);
	}

}
