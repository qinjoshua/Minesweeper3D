import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Board Class containing the 3D minesweeper board
 */
public class Board {

    /**Integer for the width of the board (y)*/
    private int width;
    /**Integer for the length of board (x)*/
    private int length;
    /** Integer for the height of the board (z)*/
    private int height;
    /**Integer for the number of mines to be placed on the board*/
    private int numMines;
    /** Boolean value that is true if onClick() is called for the first time, false otherwise*/
    private boolean firstClick = true;
    /** Array of cells containing the board the game is played on*/
    private Cell[][][] board;

    /**
     * Default constructor
     * Sets width, length, and height to 4, creating a 64 tiled board
     * Sets numMines to 18
     */
    public Board() {
        this.width = 4;
        this.length = 4;
        this.height = 4;
        this.numMines = 18;
        createBoard();
    }

    /**
     * Parameter constructor creating a board with dimension inputs
     *
     * @param width    Width of Board
     * @param length   Length of Board
     * @param height   Height of Board
     * @param numMines Number of Mines
     */
    public Board(int width, int length, int height, int numMines) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.numMines = numMines;

        createBoard();
    }

    /**
     * Randomly places a numMines number of mines on the board
     * board[0][0][0] is kept as a safeCell to ensure the user doesn't click on a mine on the first click
     */
    public void scramble() {
        int placed = 0;
        Random rand = new Random();

        while (placed < numMines) {
            int x = rand.nextInt(length);
            int y = rand.nextInt(width);
            int z = rand.nextInt(height);

            if (board[x][y][z].getMine() == false) {
                Cell mine = new MineCell(-1, true);
                if(x!=0 || y!= 0 || z!=0) {
                    board[x][y][z] = mine;
                    placed++;
                }
            }

        }
    }

    /**
     * Creates the board by filling up the 3D array with "-"
     */
    public void createBoard() {

        //initialize 3d Array and set value to default of "-"
        //dimensions = new String[this.length][this.width][this.height];
        //hasMine = new boolean[this.length][this.width][this.height];

        board = new Cell[this.length][this.length][this.height];
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                for (int k = 0; k < this.height; k++) {
                    Cell addCell = new SafeCell(0, false);
                    board[i][j][k] = addCell;
                }
            }
        }

        scramble(); //randomly place the mines by calling on the scramble() method
        changeValues();

    }

    /**
     * Test method used to print out the board.
     * Only for debugging purposes, not used in the actual program
     */
    public void printBoard() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                for (int k = 0; k < this.height; k++) {
                    System.out.print(board[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Method to return mine at specific location
     *
     * @param x length
     * @param y width
     * @param z height
     * @return 1 for mine, 0 for no mine
     */
    public int howManyMines(int x, int y, int z) {

        if (x >= 0 && x < length && y >= 0 && y < width && z >= 0 && z < height && board[x][y][z].getMine() == true) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Calculates the number of mines around a particular cell
     *
     * @param x length coordinate of cell
     * @param y width coordinate of cell
     * @param z height coordinate of cell
     * @return int # of mines around cell
     */
    public int generatePointValue(int x, int y, int z) {
        int minesNear = 0;

        minesNear += howManyMines(x - 1, y - 1, z - 1);
        minesNear += howManyMines(x - 1, y, z - 1);
        minesNear += howManyMines(x - 1, y - 1, z);
        minesNear += howManyMines(x - 1, y, z);

        minesNear += howManyMines(x - 1, y + 1, z + 1);
        minesNear += howManyMines(x - 1, y, z + 1);
        minesNear += howManyMines(x - 1, y + 1, z);
        //minesNear += howManyMines(x-1, y, z); //repeating

        minesNear += howManyMines(x, y - 1, z - 1);
        minesNear += howManyMines(x, y, z - 1);
        minesNear += howManyMines(x, y - 1, z);

        minesNear += howManyMines(x, y + 1, z + 1);
        minesNear += howManyMines(x, y, z + 1);
        minesNear += howManyMines(x, y + 1, z);

        minesNear += howManyMines(x + 1, y - 1, z - 1);
        minesNear += howManyMines(x + 1, y, z - 1);
        minesNear += howManyMines(x + 1, y - 1, z);
        minesNear += howManyMines(x + 1, y, z);

        minesNear += howManyMines(x + 1, y + 1, z + 1);
        minesNear += howManyMines(x + 1, y, z + 1);
        minesNear += howManyMines(x + 1, y + 1, z);

        minesNear += howManyMines(x + 1, y + 1, z - 1);
        minesNear += howManyMines(x + 1, y - 1, z + 1);

        minesNear += howManyMines(x - 1, y + 1, z - 1);
        minesNear += howManyMines(x - 1, y - 1, z + 1);

        minesNear += howManyMines(x, y + 1, z - 1);
        minesNear += howManyMines(x, y - 1, z + 1);

        return minesNear;
    }

    /**
     * Changes the integer state value of a particular Cell object in the board array based on the number of mines around it
     */
    public void changeValues() {

        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                for (int k = 0; k < this.height; k++) {
                    if (board[i][j][k].getMine() != true) {
                        board[i][j][k].setState(generatePointValue(i, j, k));
                    }

                }
            }
        }
    }


    /**
     * Getter method for board
     *
     * @return 3D Array of Cell Objects
     */
    public Cell[][][] getBoard() {
        return board;
    }

    public void explode() {

    }

    /**
     * Method to test if there are any mineCells adjacent to a specific Cell
     * @param length length of board
     * @param width width of board
     * @param height height of board
     * @return true if there are mines, false if there aren't any mines
     */
    public boolean adjacentMines(int length, int width, int height) {
        if (board[length][width][height].getMine() == false) {
            for (int i = length - 1; i <= length + 1; i++) {
                for (int j = width - 1; j < width + 1; j++) {
                    for (int k = height - 1; k < height + 1; k++) {
                        if (i >= 0 && j >= 0 && k >= 0 && i < this.length && j < this.width && k < this.height && board[i][j][k].getMine() == true) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Method to reveal adjacent safeCells if no mineCells are nearby.
     * In addition, sets the "Clickable" boolean for the specific cell to false (already clicked on)
     *
     * @param length length of board
     * @param width  width of board
     * @param height height of board
     */
    public void spread(int length, int width, int height) {

        if (length >= this.length || width >= this.width || height >= this.height) {
            throw new IndexOutOfBoundsException(); //error if parameters dont exist for board
        }

        if (board[length][width][height].getClickable() == true) {
            board[length][width][height].setClickable(false); //sets Clickable for that specific cell to false

            if (adjacentMines(length, width, height) == false && board[length][width][height].getMine() == false) {
                spreadToAdjacent(length, width, height); //calls on spreadToAdjacent() if no mines are nearby
            }
        }
    }

    /**
     * Helper method to call on spread() multiple time if no mines are nearby
     *
     * @param length length of board
     * @param width  width of board
     * @param height height of board
     */
    public void spreadToAdjacent(int length, int width, int height) {
        for (int i = length - 1; i <= length + 1; i++) {
            for (int j = width - 1; j < width + 1; j++) {
                for (int k = height - 1; k < height + 1; k++) {
                    if (i >= 0 && j >= 0 && k >= 0 && i < this.length && j < this.width && k < this.height) {
                        spread(i, j, k);
                    }
                }
            }
        }
    }


    public void spread2(int l, int w, int h){
        if(board[l][w][h].getState() != 0){
            //under development
        }
    }

    /**
     * OnClick Method that is called whenever the user clicks on a cell
     * @param l -length of cell
     * @param w -width of cell
     * @param h -height of cell
     * @return
     *      true if a mine is clicked
     *      false otherwise
     */
    public boolean onClick(int l, int w , int h){
        if(board[l][w][h].getMine()==true){
            if(firstClick){
                //code to change the mine to the top left hand corner
                board[l][w][h] = new SafeCell(0, false);
                board[0][0][0] = new MineCell(-1, true);
                changeValues();
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(board[l][w][h].getState() == 0){
                spread(l, w, h);
                
            }
            else
            {
            	
            }
            return true;
        }
    }

    /**
     * Getter method to return a single cell
     * @param l -length of cell
     * @param w -width of cell
     * @param h -height of cell
     * @return a specific cell
     */
    public Cell getCell(int length, int width, int height){
        return board[length][width][height];
    }
}



