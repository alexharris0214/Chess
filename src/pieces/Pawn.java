package pieces;

import java.util.ArrayList;

/**
 * @author David Katri
 * @author Alex Harris
 * child of the piece class (extends the piece class)
 * contains several methods relevant to the Pawn in chess
*/
public class Pawn extends Piece {
    /**
     * @field hasMoved2 a boolean to tell if the Pawn has moved 2 spots or not
    */
    public boolean hasMoved2;

    /** 
     * Pawn constructor with all fields as parameters
     * @param row the row or rank of the pawn on the board
     * @param column the column or file of the pawn on the board
     * @param color the color of the pawn (block, white)
    */
    public Pawn(int row, int column, int color){
        super(row, column, color);
    }

    /**
     * overrides the toString() from the Piece class to be specific to Pawn
    */
    @Override
    public String toString(){
        return ("[Pawn]  Row: " +this.row + " Column: "  + column +  " Color: " + color);
    }

    /**
    * Finds all possible available move for current piece of type Pawn
    * Returns all possible moves in the form of a 2D array
    * The first colum is the row, and the second column is the column, 
    * both respective to the position it is on
    * the board
    * @param firstFile the file of the pawn
    * @param firstRank the rank of the pawn
    * @param board the chess board with current piece positions
    * @return an ArrayList of all available moves the pawn can make
    */
    public ArrayList<int[]> findAvaiableMoves(int firstFile, int firstRank, Piece[][] board){
        ArrayList<int[]> moves = new ArrayList<>();

        int color = board[firstFile][firstRank].color;

        ArrayList<int[]> previousBlack = new ArrayList<>();
        ArrayList<int[]> previousWhite = new ArrayList<>();
        // checking to see if pawn is black
        // in which it can only move downwards
        if(color == 0){
            // chekcing to see if pawn has moved yet or not, if it has not, then move it down 2
            if(firstFile == 1){
                int[] arr = {firstFile+2, firstRank};

                // checking to see if position is empty or not
                // if it is, add it
                try{
                    if(board[firstFile+2][firstRank] == null){
                        moves.add(arr);
                        int[] arr2 = {firstFile+2, firstRank};
                        previousBlack.add(arr2);
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    // do nothing 
                }
            }

            // checking if pawn is able to take enemy player or not
            try{
                if(board[firstFile +1][firstRank-1] instanceof Piece){
                    Piece temp = board[firstFile +1][firstRank-1];
                    if(temp.color != color){
                        int[] arr3 = {firstFile+1, firstRank -1};
                        moves.add(arr3);
                    } 
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
            try{
                if(board[firstFile +1][firstRank +1] instanceof Piece){
                    if(board[firstFile +1][firstRank +1].color != color){
                        int[] arr4 = {firstFile+1, firstRank+1};
                        moves.add(arr4);
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }

            try{      
                // finally, checking to see if pawn can move down one or not
                if(board[firstFile+1][firstRank] == null){
                    int[] arr5 = {firstFile +1, firstRank};
                    moves.add(arr5);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }

            // checking en passant condition
            try{
                if(board[firstFile][firstRank+1] instanceof Pawn){
                    int tempcolor = board[firstFile][firstRank+1].color;
                    if(color!=tempcolor){
                        Pawn p = (Pawn) board[firstFile][firstRank+1];
                        if(p.hasMoved2){
                            int[] arr = {firstFile+1, firstRank+1};
                            moves.add(arr);
                        }
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
            try{
                if(board[firstFile][firstRank-1] instanceof Pawn){
                    int tempcolor = board[firstFile][firstRank-1].color;
                    if(color!=tempcolor){
                        Pawn p = (Pawn) board[firstFile][firstRank-1];
                        if(p.hasMoved2){
                            int[] arr = {firstFile+1, firstRank-1};
                            moves.add(arr);
                        }
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
        } else { // pawn is White

            // chekcing to see if pawn has moved yet or not, if it has not, then move it down 2
            if(firstFile == 6){
                int[] arr = {firstFile-2, firstRank};

                // checking to see if position is empty or not
                // if it is, add it
                try{
                    if(board[firstFile-2][firstRank] == null){
                        moves.add(arr);
                        int[] arr2 = {firstFile-2, firstRank};
                        previousWhite.add(arr2);
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                    // do nothing 
                }
            }

            // checking if pawn is able to take enemy player or not
            try{
                if(board[firstFile -1][firstRank-1] instanceof Piece){
                    Piece temp = board[firstFile -1][firstRank-1];
                    if(temp.color != color){
                        int[] arr3 = {firstFile-1, firstRank -1};
                        moves.add(arr3);
                    } 
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
            try{
                if(board[firstFile -1][firstRank +1] instanceof Piece){
                    if(board[firstFile - 1][firstRank +1].color != color){
                        int[] arr4 = {firstFile-1, firstRank+1};
                        moves.add(arr4);
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }

            try{      
                // finally, checking to see if pawn can move down one or not
                if(board[firstFile-1][firstRank] == null){
                    int[] arr5 = {firstFile -1, firstRank};
                    moves.add(arr5);
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }

            // checking en passant condition
            try{
                if(board[firstFile][firstRank+1] instanceof Pawn){
                    int tempcolor = board[firstFile][firstRank+1].color;
                    if(color!=tempcolor){
                        Pawn p = (Pawn) board[firstFile][firstRank+1];
                        if(p.hasMoved2){
                            int[] arr = {firstFile-1, firstRank+1};
                            moves.add(arr);
                        }
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
            try{
                if(board[firstFile][firstRank-1] instanceof Pawn){
                    int tempcolor = board[firstFile][firstRank-1].color;
                    if(color!=tempcolor){
                        Pawn p = (Pawn) board[firstFile][firstRank-1];
                        if(p.hasMoved2){
                            int[] arr = {firstFile-1, firstRank-1};
                            moves.add(arr);
                        }
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e){
                // do nothing
            }
        }
        return moves;
    }
}
