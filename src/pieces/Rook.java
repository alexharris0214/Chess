package pieces;

import java.util.ArrayList;

/**
 * @author David Katri
 * @author Alex Harris
 * child of the piece class (extends the piece class)
 * contains several methods relevant to the Rook in chess
*/
public class Rook extends Piece{
    /**
     * @field hasMoved a boolean to tell if the rook has moved or not for castling
     * @field originalSpot an Arraylist that stores the original spot to be used for castling
    */
    public boolean hasMoved = false;
    public ArrayList<int[]> originalSpot = new ArrayList<>();

    /** 
     * Rook constructor with all fields as parameters
     * @param row the row or rank of the rook on the board
     * @param column the column or file of the rook on the board
     * @param color the color of the rook (block, white)
    */
    public Rook(int row, int column, int color){
        super(row, column, color);
    }

    /**
     * overrides the toString() from the Piece class to be specific to Rook
    */
    @Override
    public String toString(){
        return ("[Rook]  Row: " +this.row + " Column: "  + column +  " Color: " + color);
    }

    /**
    * Finds all possible available move for current piece of type Rook
    * Returns all possible moves in the form of a 2D array
    * The first colum is the row, and the second column is the column, 
    * both respective to the position it is on
    * the board
    * @param firstFile the file of the rook
    * @param firstRank the rank of the rook
    * @param board the chess board with current piece positions
    * @return an ArrayList of all available moves the rook can make
    */
    public ArrayList<int[]> findAvaiableMoves(int firstFile, int firstRank, Piece[][] board){
        ArrayList<int[]> moves = new ArrayList<>();

        int color = board[firstFile][firstRank].color;

        // moving upwards
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile - i][firstRank] instanceof Piece){
                    if(board[firstFile - i][firstRank].color != color){
                        int[] arr = {firstFile-i, firstRank};
                        moves.add(arr);
                        break;
                    } else{
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile-i, firstRank};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }
        
        // moving down
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile + i][firstRank] instanceof Piece){
                    if(board[firstFile + i][firstRank].color != color){
                        int[] arr = {firstFile+i, firstRank};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile+i, firstRank};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }   
        }

        // moving right
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile][firstRank-i] instanceof Piece){
                    if(board[firstFile][firstRank-i].color != color){
                        int[] arr = {firstFile, firstRank-i};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile, firstRank-i};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }

        // moving left
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile][firstRank+i] instanceof Piece){
                    if(board[firstFile][firstRank+i].color != color){
                        int[] arr = {firstFile, firstRank+i};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile, firstRank+i};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
        }
        return moves;
    }
}
