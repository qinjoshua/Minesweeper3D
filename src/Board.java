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
    //private String[][][] dimensions;
    //private boolean[][][] hasMine;
    private Cell[][][] board;

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

            if(board[x][y][z].getMine()==false){
                Cell mine = new MineCell(-1, true);
                board[x][y][z] = mine;
                placed++;
            }
        }
    }

    /**
     * Creates the board by filling up the 3D array with "-"
     */
    public void createBoard(){

        //initialize 3d Array and set value to default of "-"
        //dimensions = new String[this.length][this.width][this.height];
        //hasMine = new boolean[this.length][this.width][this.height];

        board = new Cell[this.length][this.length][this.height];
        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.width; j++){
                for(int k=0; k<this.height; k++){
                    Cell addCell = new SafeCell(0, false);
                    board[i][j][k] = addCell;
                }
            }
        }

        scramble(); //randomly place the mines by calling on the scramble() method
        changeValues();

    }

    public void printBoard(){
        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.width; j++){
                for(int k=0; k<this.height; k++){
                    System.out.print(board[i][j][k] + " ");
                    //System.out.print(board[i][j][k].getState() + " ");
                    //System.out.println(Arrays.deepToString(board));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Method to return mine at specific location
     * @param x length
     * @param y width
     * @param z height
     * @return 1 for mine, 0 for no mine
     */
    public int howManyMines(int x, int y, int z){

        if(x>=0 && x<length && y>=0 && y<width && z>=0 && z<height && board[x][y][z].getMine() == true){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Calculates the number of mines around a particular cell
     * @param x length coordinate of cell
     * @param y width coordinate of cell
     * @param z height coordinate of cell
     * @return int # of mines around cell
     */
    public int generatePointValue(int x, int y, int z){
        int minesNear = 0;

        minesNear += howManyMines(x-1, y-1, z-1);
        minesNear += howManyMines(x-1, y, z-1);
        minesNear += howManyMines(x-1, y-1, z);
        minesNear += howManyMines(x-1, y, z);

        minesNear += howManyMines(x-1, y+1, z+1);
        minesNear += howManyMines(x-1, y, z+1);
        minesNear += howManyMines(x-1, y+1, z);
        //minesNear += howManyMines(x-1, y, z); //repeating

        minesNear += howManyMines(x, y-1, z-1);
        minesNear += howManyMines(x, y, z-1);
        minesNear += howManyMines(x, y-1, z);

        minesNear += howManyMines(x, y+1, z+1);
        minesNear += howManyMines(x, y, z+1);
        minesNear += howManyMines(x, y+1, z);

        minesNear += howManyMines(x+1, y-1, z-1);
        minesNear += howManyMines(x+1, y, z-1);
        minesNear += howManyMines(x+1, y-1, z);
        minesNear += howManyMines(x+1, y, z);

        minesNear += howManyMines(x+1, y+1, z+1);
        minesNear += howManyMines(x+1, y, z+1);
        minesNear += howManyMines(x+1, y+1, z);

        minesNear += howManyMines(x+1, y+1, z-1);
        minesNear += howManyMines(x+1, y-1, z+1);

        minesNear += howManyMines(x-1, y+1, z-1);
        minesNear += howManyMines(x-1, y-1, z+1);

        minesNear += howManyMines(x, y+1, z-1);
        minesNear += howManyMines(x, y-1, z+1);

        return minesNear;
    }

    /**
     * Changes the integer state value of a particular Cell object in the board array based on the number of mines around it
     */
    public void changeValues(){

        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.width; j++){
                for(int k=0; k<this.height; k++){
                    if(board[i][j][k].getMine() != true){
                        board[i][j][k].setState(generatePointValue(i, j, k));
                    }
                }
            }
        }
    }


    /**
     * Getter method for board
     * @return 3D Array of Cell Objects
     */
    public Cell[][][] getBoard() {
        return board;
    }

    public void explode(){
        //to be implemented later
    }


    public void spread(){
        //to be implemented later
    }
}
