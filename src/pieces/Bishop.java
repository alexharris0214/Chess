package pieces;

import java.util.ArrayList;

/**
 * @author David Katri
 * @author Alex Harris
 * child of the piece class (extends the piece class)
 * contains several methods relevant to the Bishop in chess
*/
public class Bishop extends Piece{
    /** 
     * Bishop constructor with all fields as parameters
     * @param row the row or rank of the bishop on the board
     * @param column the column or file of the bishop on the board
     * @param color the color of the bishop (block, white)
    */
    public Bishop(int row, int column, int color){
        super(row, column, color);
    }

    /**
     * overrides the toString() from the Piece class to be specific to Bishop
    */
    @Override
    public String toString(){
        return ("[Bishop]  Row: " +this.row + " Column: "  + column +  " Color: " + color);
    }

    /**
    * Finds all possible available move for current piece of type Bishop
    * Returns all possible moves in the form of a 2D array
    * The first colum is the row, and the second column is the column, 
    * both respective to the position it is on
    * the board
    * @param firstFile the file of the bishop
    * @param firstRank the rank of the bishop
    * @param board the chess board with current piece positions
    * @return an ArrayList of all available moves the bishop can make
    */
    public ArrayList<int[]> findAvaiableMoves(int firstFile, int firstRank, Piece[][] board){
        ArrayList<int[]> moves = new ArrayList<>();

        int color = board[firstFile][firstRank].color;

        //moving diagnol top left
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile - i][firstRank + i] instanceof Piece){
                    if(board[firstFile - i][firstRank + i].color != color){
                        int[] arr = {firstFile-i, firstRank+i};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile-i, firstRank+i};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }

        //moving diagnol top right
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile + i][firstRank + i] instanceof Piece){
                    if(board[firstFile + i][firstRank + i].color != color){
                        int[] arr = {firstFile+i, firstRank+i};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile+i, firstRank+i};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }

        //moving diagnol bottom left
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile - i][firstRank - i] instanceof Piece){
                    if(board[firstFile - i][firstRank - i].color != color){
                        int[] arr = {firstFile-i, firstRank-i};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile-i, firstRank-i};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }

        //moving diagnol bottom right
        for(int i = 1; i<8; i++){
            try{
                // if the position is held by a piece
                // then we need to check to see if that piece is opponents or ours
                // if it is opponents, include it in possible move
                if(board[firstFile + i][firstRank - i] instanceof Piece){
                    if(board[firstFile + i][firstRank - i].color != color){
                        int[] arr = {firstFile+i, firstRank-i};
                        moves.add(arr);
                        break;
                    } else {
                        break;
                    }
                // will reach is position is an empty spot
                // in which case it is a legal move
                } else {
                    int[] arr = {firstFile+i, firstRank-i};
                    moves.add(arr);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }
        return moves;
    }
}