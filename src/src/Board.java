import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Board {

    private int width;
    private int length;
    private int height;
    private int numMines;
    private String[][][] dimensions;

    public Board(){
        this.width = 4;
        this.length = 4;
        this.height = 4;
        this.numMines = 18;
        createBoard();
    }

    public Board(int width, int length, int height, int numMines){
        this.width = width;
        this.length = length;
        this.height = height;
        this.numMines = numMines;

        createBoard();
    }

    public void scramble(){
        int placed = 0;
        Random rand = new Random();

        while(placed < numMines){
            int x = rand.nextInt(length);
            int y= rand.nextInt(width);
            int z = rand.nextInt(height);

            if(dimensions[x][y][z] != "*"){
                dimensions[x][y][z] = "*";
                placed++;
            }
        }
    }

    public void createBoard(){

        //initialize 3d Array and set value to default of "-"
        dimensions = new String[this.length][this.width][this.height];
        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.width; j++){
                for(int k=0; k<this.height; k++){
                    dimensions[i][j][k] = "-";
                }
            }
        }

        scramble(); //randomly place the mines by calling on the scramble() method

    }

    public void explode(){

    }

    public void generatePointValue(){

    }

    public String[][][] getDimensions() {
        return dimensions;
    }

}
