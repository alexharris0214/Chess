package pieces;

import java.util.ArrayList;

/**
 * @author David Katri
 * @author Alex Harris
 * child of the piece class (extends the piece class)
 * contains several methods relevant to the Knight in chess
*/
public class Knight extends Piece{
    /** 
     * Knight constructor with all fields as parameters
     * @param row the row or rank of the knight on the board
     * @param column the column or file of the knight on the board
     * @param color the color of the knight (block, white)
    */
    public Knight(int row, int column, int color){
        super(row, column, color);
    }

    /**
     * overrides the toString() from the Piece class to be specific to Knight
    */
    @Override
    public String toString(){
        return ("[Knight]  Row: " +row + " Column: "  + column +  " Color: " + color);
    }

    /**
    * Finds all possible available move for current piece of type Knight
    * Returns all possible moves in the form of a 2D array
    * The first colum is the row, and the second column is the column, 
    * both respective to the position it is on
    * the board
    * @param firstFile the file of the knight
    * @param firstRank the rank of the knight
    * @param board the chess board with current piece positions
    * @return an ArrayList of all available moves the knight can make
    */
    public ArrayList<int[]> findAvaiableMoves(int firstFile, int firstRank, Piece[][] board){
        ArrayList<int[]> moves = new ArrayList<>();
        int color = board[firstFile][firstRank].color;

        //checks for all different 1,2 combos for knight
        //left 1 file up 2 ranks
        try{
            if(board[firstFile-1][firstRank+2] instanceof Piece){
                if(board[firstFile-1][firstRank+2].color != color){
                    int[] arr1 = {firstFile-1,firstRank+2};
                    moves.add(arr1);
                }
            } else{
                int arr1[] = {firstFile-1,firstRank+2};
                moves.add(arr1);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //right 1 file up 2 ranks
        try{
            if(board[firstFile+1][firstRank+2] instanceof Piece){
                if(board[firstFile+1][firstRank+2].color != color){
                    int[] arr2 = {firstFile+1,firstRank+2};
                    moves.add(arr2);
                }
            } else{
                int[] arr2 = {firstFile+1,firstRank+2};
                moves.add(arr2);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //left 2 files up 1 rank
        try{
            if(board[firstFile-2][firstRank+1] instanceof Piece){
                if(board[firstFile-2][firstRank+1].color != color){
                    int[] arr3 = {firstFile-2,firstRank+1};
                    moves.add(arr3);
                }
            } else{
                int[] arr3 = {firstFile-2,firstRank+1};
                moves.add(arr3);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //right 2 files up 1 rank
        try{
            if(board[firstFile+2][firstRank+1] instanceof Piece){
                if(board[firstFile+2][firstRank+1].color != color){
                    int arr4[] = {firstFile+2,firstRank+1};
                    moves.add(arr4);
                }
            } else{
                int arr4[] = {firstFile+2,firstRank+1};
                moves.add(arr4);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //left 1 file down 2 ranks
        try{
            if(board[firstFile-1][firstRank-2] instanceof Piece){
                if(board[firstFile-1][firstRank-2].color != color){
                    int[] arr5 = {firstFile-1,firstRank-2};
                    moves.add(arr5);
                }
            } else{
                int[] arr5 = {firstFile-1,firstRank-2};
                    moves.add(arr5);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //right 1 file down 2 ranks
        try{
            if(board[firstFile+1][firstRank-2] instanceof Piece){
                if(board[firstFile+1][firstRank-2].color != color){
                    int[] arr6 = {firstFile+1,firstRank-2};
                    moves.add(arr6);
                }
            } else{
                int[] arr6 = {firstFile+1,firstRank-2};
                moves.add(arr6);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //left 2 files down 1 rank
        try{
            if(board[firstFile-2][firstRank-1] instanceof Piece){
                if(board[firstFile-2][firstRank-1].color != color){
                    int[] arr7 = {firstFile-2,firstRank-1};
                    moves.add(arr7);
                }
            } else{
                int[] arr7 = {firstFile-2,firstRank-1};
                moves.add(arr7);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        //right 2 files down 1 rank
        try{
            if(board[firstFile+2][firstRank-1] instanceof Piece){
                if(board[firstFile+2][firstRank-1].color != color){
                    int[] arr8 = {firstFile+2,firstRank-1};
                    moves.add(arr8);
                }
            } else{
                int[] arr8 = {firstFile+2,firstRank-1};
                moves.add(arr8);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }

        return moves;
    }
}