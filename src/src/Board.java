public class Board {

    private int width;
    private int length;
    private int height;
    private int numMines;


    public Board(){
        this.width = 9;
        this.length = 9;
        this.height = 9;
    }

    public Board(int width, int length, int height, int numMines){
        this.width = width;
        this.length = length;
        this.height = height;
        this.numMines = numMines;
    }


}
s