import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Board Class containing the 3D minesweeper board
 */
public class Board {

    private int width;
    private int length;
    private int height;
    private int numMines;
    private String[][][] dimensions;
    private boolean[][][] hasMine;

    /**
     * Default constructor
     * Sets width, length, and height to 4, creating a 64 tiled board
     * Sets numMines to 18
     */
    public Board(){
        this.width = 4;
        this.length = 4;
        this.height = 4;
        this.numMines = 18;
        createBoard();
    }

    /**
     * Parameter constructor creating a board with dimension inputs
     * @param width Width of Board
     * @param length Length of Board
     * @param height Height of Board
     * @param numMines Number of Mines
     */
    public Board(int width, int length, int height, int numMines){
        this.width = width;
        this.length = length;
        this.height = height;
        this.numMines = numMines;

        createBoard();
    }

    /**
     * Randomly places a numMines number of mines on the board
     */
    public void scramble(){
        int placed = 0;
        Random rand = new Random();

        while(placed < numMines){
            int x = rand.nextInt(length);
            int y= rand.nextInt(width);
            int z = rand.nextInt(height);

            if(hasMine[x][y][z] != true){
                hasMine[x][y][z] = true;
                placed++;
            }
        }
    }

    /**
     * Creates the board by filling up the 3D array with "-"
     */
    public void createBoard(){

        //initialize 3d Array and set value to default of "-"
        dimensions = new String[this.length][this.width][this.height];
        hasMine = new boolean[this.length][this.width][this.height];
        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.width; j++){
                for(int k=0; k<this.height; k++){
                    dimensions[i][j][k] = "-";
                    hasMine[i][j][k] = false;
                }
            }
        }

        scramble(); //randomly place the mines by calling on the scramble() method

    }

    public void explode(){
        //to be implemented later
    }

    public void generatePointValue(){

    }

    public void spread(){

    }

    public String[][][] getDimensions() {
        return dimensions;
    }

}
