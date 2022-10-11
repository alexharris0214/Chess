package pieces;

import java.util.ArrayList;

/**
 * @author David Katri
 * @author Alex Harris
 * child of the piece class (extends the piece class)
 * contains several methods relevant to the King in chess
*/
public class King extends Piece{
    /**
     * @param hasMoved a boolean to tell if the king has moved or not for castling
    */
    public boolean hasMoved = false;

    /** 
     * King constructor with all fields as parameters
     * @param row the row or rank of the king on the board
     * @param column the column or file of the king on the board
     * @param color the color of the king (block, white)
    */
    public King(int row, int column, int color){
        super(row, column, color);
    }

    /**
     * overrides the toString() from the Piece class to be specific to King
    */
    @Override
    public String toString(){
        return ("[King]  Row: " +this.row + " Column: "  + column +  " Color: " + color);
    }

    /**
    * Finds all possible available move for current piece of type King
    * Returns all possible moves in the form of a 2D array
    * The first colum is the row, and the second column is the column, 
    * both respective to the position it is on
    * the board
    * @param firstFile the file of the king
    * @param firstRank the rank of the king
    * @param board the chess board with current piece positions
    * @return an ArrayList of all available moves the king can make
    */
    public ArrayList<int[]> findAvaiableMoves(int firstFile, int firstRank, Piece[][] board){
        ArrayList<int[]> moves = new ArrayList<>();
        
        int color = board[firstFile][firstRank].color;
        
        try{
            // checking if King can move down
            if(board[firstFile + 1][firstRank] instanceof Piece){
                if(board[firstFile +1][firstRank].color != color){
                    int[] arr1 = {firstFile + 1, firstRank};
                    moves.add(arr1);
                }
            } else {
                int[] arr1 = {firstFile + 1, firstRank};
                moves.add(arr1);  
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move up
            if(board[firstFile - 1][firstRank] instanceof Piece){
                if(board[firstFile -1][firstRank].color != color){
                    int[] arr2 = {firstFile - 1, firstRank};
                    moves.add(arr2);
                }
            } else {
                int[] arr2 = {firstFile - 1, firstRank};
                moves.add(arr2);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move right
            if(board[firstFile][firstRank +1] instanceof Piece){
                if(board[firstFile][firstRank+1].color != color){
                    int[] arr3 = {firstFile, firstRank+1};
                    moves.add(arr3);
                }
            } else {
                int[] arr3 = {firstFile, firstRank+1};
                moves.add(arr3);  
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move left
            if(board[firstFile][firstRank -1] instanceof Piece){
                if(board[firstFile][firstRank-1].color != color){
                    int[] arr4 = {firstFile, firstRank-1};
                    moves.add(arr4);
                }
            } else {
                int[] arr4 = {firstFile, firstRank-1};
                moves.add(arr4);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move down-right
            if(board[firstFile+1][firstRank +1] instanceof Piece){
                if(board[firstFile+1][firstRank+1].color != color){
                    int[] arr5 = {firstFile+1, firstRank+1};
                    moves.add(arr5);
                }
            } else {
                int[] arr5 = {firstFile+1, firstRank+1};
                moves.add(arr5);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move down-left
            if(board[firstFile+1][firstRank -1] instanceof Piece){
                if(board[firstFile+1][firstRank-1].color != color){
                    int[] arr6 = {firstFile+1, firstRank-1};
                    moves.add(arr6);
                }
            } else {
                int[] arr6 = {firstFile+1, firstRank-1};
                moves.add(arr6);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move up-right
            if(board[firstFile-1][firstRank +1] instanceof Piece){
                if(board[firstFile-1][firstRank+1].color != color){
                    int[] arr7 = {firstFile-1, firstRank+1};
                    moves.add(arr7);
                }
            } else {
                int[] arr7 = {firstFile-1, firstRank+1};
                moves.add(arr7);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }

        try{
            // checking if King can move up-left
            if(board[firstFile-1][firstRank -1] instanceof Piece){
                if(board[firstFile-1][firstRank-1].color != color){
                    int[] arr8 = {firstFile-1, firstRank-1};
                    moves.add(arr8);
                }
            } else {
                int[] arr8 = {firstFile-1, firstRank-1};
                moves.add(arr8);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            // do nothing
        }
        
        return moves;
    }
}