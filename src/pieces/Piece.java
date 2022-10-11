package pieces;

import java.util.ArrayList;

/**
 * @author David Katri
 * @author Alex Harris
 * Parent class for all pieces
 * each piece shares common fields such as, position on the board and what player it belongs to
 * each individual piece class (pawn, rook, etc..) is a subclass of Piece class
*/

public abstract class Piece{
    /**
     * @param row the row or rank of the piece on the board
     * @param column the column or file of the piece on the board
     * @param color the color of the piece (block, white)
    */
    public int row;
    public int column;
    public int color;

    /**
     * Piece constructor with all fields as parameters
     * @param row the row or rank of the piece on the board
     * @param column the column or file of the piece on the board
     * @param color the color of the piece (block, white)
    */
    protected Piece(int row, int column, int color){
        this.row = row;
        this.column = column;
        this.color = color;
    }

    /**
     * The default toString() methos of a piece
    */
    public String toString(){
        return ("[Piece]  Row: " +row + " Column: "  + column +  " Color: " + color);
    }

    /**
    * Abstract method to be implemented by each child of Piece
    * Returns all possible moves in the form of a 2D array
    * The first colum is the row, and the second column is the column, 
    * both respective to the position it is on
    * the board
    * @param firstFile the file of the piece
    * @param firstRank the rank of the piece
    * @param board the chess board with current piece positions
    * @return an ArrayList of all available moves the piece can make
    */
    public abstract ArrayList<int[]> findAvaiableMoves(int firstFile, int firstRank, Piece[][] board);
}

