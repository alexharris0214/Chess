package board;
import java.util.ArrayList;
import pieces.*;
import game.Chess;

/**
 * @author David Katri
 * @author Alex Harris
 * Board class contains logic for handling the board operations
*/

public class Board {
    public static int ROWS = 8;
    public static int COLUMNS = 8;
    public Piece[][] data;
    
    /**
     * @param Board created Board object that contains...
     * @param Rows number of rows
     * @param Columns number of columns
     * @param data the two dimensional array of Pieces that resembled the chess grid
    */
    public Board(){
        data = new Piece[ROWS][COLUMNS];
        int row=0;
        for(int i = 0; i<2; i++){
            if(i==1){
                row = 7;
            }
            data[row][0] = new Rook(row, 0, i);
            data[row][1] = new Knight(row, 1, i);
            data[row][2] = new Bishop(row, 2, i);
            data[row][3] = new Queen(row, 3, i);
            data[row][4] = new King(row, 4, i);
            data[row][5] = new Bishop(row, 5, i);
            data[row][6] = new Knight(row, 6, i);
            data[row][7] = new Rook(row, 7, i);
        }
        row = 1;
        for(int j=0; j<2; j++){
            if(j==1){
                row = 6;
            }
            for(int n = 0; n<8; n++){
                data[row][n] = new Pawn(row, n, j);
            }
        }
    } 
    /**  
    * draws the current board according the specificatoins
    * does not return anything
    * @param firstDraw contains logic if the board is being drawn for the first time for format reasons
    */
    public void drawBoard(boolean firstDraw){
        int color = 0;
        for(int i= 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                //grabbing the color of the Piece
                // done at the beginning so we only have to call it once
                if(data[i][j] instanceof Piece){
                    color = data[i][j].color;
                }
                if(data[i][j] instanceof Pawn){
                    if(color == 0){
                        System.out.print("bp ");
                    } else {
                        System.out.print("wp ");           
                    }
                } else if(data[i][j] instanceof Rook){
                    if(firstDraw == true){
                        Rook r = (Rook) data[i][j];
                        int[] arr = {i,j};
                        r.originalSpot.add(0, arr);
                    }
                    if(color == 0){
                        System.out.print("bR ");
                    } else {
                        System.out.print("wR ");           
                    }
                } else if(data[i][j] instanceof Knight){
                    if(color == 0){
                        System.out.print("bN ");
                    } else {
                        System.out.print("wN ");           
                    }
                } else if(data[i][j] instanceof Bishop){
                    if(color == 0){
                        System.out.print("bB ");
                    } else {
                        System.out.print("wB ");           
                    }
                } else if(data[i][j] instanceof Queen){
                    if(color == 0){
                        System.out.print("bQ ");
                    } else {
                        System.out.print("wQ ");           
                    }
                } else if(data[i][j] instanceof King){
                    if(color == 0){
                        System.out.print("bK ");
                    } else {
                        System.out.print("wK ");           
                    }
                } else {
                    color = getDefaultSpot(i, j);
                    if(color == 0){
                        System.out.print("## ");
                    } else {
                        System.out.print("   ");
                    }

                }
            }
            System.out.println(8-i);
        }
        System.out.println(" a  b  c  d  e  f  g  h");
    }
    /**
     *  gets the color of the default tile on the board
        returns 0 for black
        returns 1 for white
     * @param row is the row we are accessing
     * @param column is the column we are accesing
     * @return returns 0 or 1 regarding to if the tile is white(1) or black(0)
     */
    public int getDefaultSpot(int row, int column){
        if (row == 0 || row == 2 || row == 4 || row == 6){
            if (column == 1 || column == 3 || column == 5 || column == 7){
                return 0;
            } else{
                return 1;
            }
    	} else {
            if (column == 1 || column == 3 || column == 5 || column == 7){
                return 1;
            }
            else{
                return 0;
            }
	    }
    }

    
    /**
     * Moves piece on the board
        calls pieces respective legal move checker method via dynamic binding
        returns true if move was legal
        returns false otherwise
     * @param rowOriginal row of the original position
     * @param columnOriginal column of the original position
     * @param rowNew row of the new position
     * @param columnNew row of the new column
     * @param color color that the original piece is
     * @return returns true is move was succesful, or false if move was not succesful
     */
    public boolean move(int rowOriginal, int columnOriginal, int rowNew, int columnNew, int color){
        if(!((data[rowOriginal][columnOriginal]) instanceof Piece)){
            return false;
        }
        Piece temp = data[rowOriginal][columnOriginal];
        if(temp.color != color){
            return false;
        }

        //if instance of King, check for castling
        if(data[rowOriginal][columnOriginal] instanceof King){
            //if King is white
            if(color == 1){
                //if the King wants to castle right
                if(rowOriginal == 7 && rowNew == 7 && columnNew == 6){
                    //if castling is actually valid, do it
                    if(canCastle(color,columnNew)){
                        King k = (King) temp;
                        data[rowOriginal][columnOriginal] = null;
                        //make sure king does not go through check
                        for(int i = 5; i <= 6;i++){
                            data[7][i] = k;
                            if(isKingInCheck(color)){
                                data[7][i] = null;
                                data[7][4] = k;
                                return false;
                            }
                            data[7][i] = null;
                        }
                        data[rowNew][columnNew] = k;
                        data[7][7] = null;
                        data[7][5] = new Rook(7,5,color);
                        Rook r = (Rook) data[7][5];
                        k.hasMoved = true;
                        r.hasMoved = true;
                        return true;
                    }
                }
                //if the King wants to castle left
                if(rowOriginal == 7 && rowNew == 7 && columnNew == 2){
                    //if castling is actually valid, do it
                    if(canCastle(color,columnNew)){
                        King k = (King) temp;
                        data[rowOriginal][columnOriginal] = null;
                        //make sure king does not go through check
                        for(int i = 2; i <= 3;i++){
                            data[7][i] = k;
                            if(isKingInCheck(color)){
                                data[7][i] = null;
                                data[7][4] = k;
                                return false;
                            }
                            data[7][i] = null;
                        }
                        data[rowNew][columnNew] = k;
                        data[7][0] = null;
                        data[7][3] = new Rook(7,3,color);
                        Rook r = (Rook) data[7][3];
                        k.hasMoved = true;
                        r.hasMoved = true;
                        return true;
                    }
                }
            }
            //if King is black
            if(color == 0){
                //if King wants to castle right
                if(rowOriginal == 0 && rowNew == 0 && columnNew == 6){
                    //if castling is actually valid, do it
                    if(canCastle(color,columnNew)){
                        King k = (King) temp;
                        data[rowOriginal][columnOriginal] = null;
                        //make sure king does not go through check
                        for(int i = 5; i <= 6;i++){
                            data[0][i] = k;
                            if(isKingInCheck(color)){
                                data[0][i] = null;
                                data[0][4] = k;
                                return false;
                            }
                            data[0][i] = null;
                        }
                        data[rowNew][columnNew] = k;
                        data[0][7] = null;
                        data[0][5] = new Rook(0,5,color);
                        Rook r = (Rook) data[0][5];
                        k.hasMoved = true;
                        r.hasMoved = true;
                        return true;
                    }
                }
                //if the King wants to castle left
                if(rowOriginal == 0 && rowNew == 0 && columnNew == 2){
                    //if castling is actually valid, do it
                    if(canCastle(color,columnNew)){
                        King k = (King) temp;
                        data[rowOriginal][columnOriginal] = null;
                        //make sure king does not go through check
                        for(int i = 2; i <= 3;i++){
                            data[0][i] = k;
                            if(isKingInCheck(color)){
                                data[0][i] = null;
                                data[0][4] = k;
                                return false;
                            }
                            data[0][i] = null;
                        }
                        data[rowNew][columnNew] = k;
                        data[0][0] = null;
                        data[0][3] = new Rook(0,3,color);
                        Rook r = (Rook) data[0][3];
                        k.hasMoved = true;
                        r.hasMoved = true;
                        return true;
                    }
                }
            }
        }

        ArrayList<int[]> moves = temp.findAvaiableMoves(rowOriginal, columnOriginal, data);
        for(int i=0; i<moves.size(); i++){
            int[] move = moves.get(i);
            if(move[0] == rowNew && move[1] == columnNew){
                if(data[rowOriginal][columnOriginal] instanceof Pawn){
                    Pawn p = (Pawn) temp;
                    if(Math.abs(rowOriginal-rowNew) == 2){
                        p.hasMoved2 = true;
                    }
                    data[rowOriginal][columnOriginal] = null;
                    data[rowNew][columnNew] = p;
                } 
                if(data[rowOriginal][columnOriginal] instanceof Rook){
                    Rook r = (Rook) temp;
                    data[rowOriginal][columnOriginal] = null;
                    data[rowNew][columnNew] = r;
                    r.hasMoved = true;
                }
                if(data[rowOriginal][columnOriginal] instanceof King){
                    King k = (King) temp;
                    data[rowOriginal][columnOriginal] = null;
                    data[rowNew][columnNew] = k;
                    k.hasMoved = true;
                }
                else {
                    data[rowOriginal][columnOriginal] = null;
                    data[rowNew][columnNew] = temp;
                }
                return true;
            } 
        }
        return false;
    } 
    /**
     * 
     * @param color the color of the player
     * @return returns true if piece can promote, false if not
     */
    public boolean canPromote(int color){
        for(int i = 0; i<2; i++){
            for(int j = 0; j<8; j++){
                if(data[i*5 + 1][j] instanceof Pawn){
                    int pawnColor = data[i*5 + 1][j].color;
                    if(((pawnColor==0 && i ==1) && (pawnColor == color)) || ((pawnColor == 1 && i == 0)  && (pawnColor == color))){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    /**
     * 
     * @param entry handles the users entry to see if it was a promote action
     * @param color color of player
     */
    public void handlePromote(String entry, int color){
        String[] moves = entry.split(" ");
        int[] move = Chess.parseMove(moves[0] + " " + moves[1]);
        move(move[1], move[0], move[3], move[2], color);
        if( (move[1] == 6 || move[1] ==1)   ){
            if(moves.length >2){
                String id = moves[2];
                switch(id){
                    case("Q"):
                        data[move[3]][move[2]] = new Queen(move[3], move[2], color);
                        break;
                    case("N"):
                        data[move[3]][move[2]] = new Knight(move[3], move[2], color);
                        break;
                    case("R"):
                        data[move[3]][move[2]] = new Rook(move[3], move[2], color);
                        break;
                    case("B"):
                        data[move[3]][move[2]] = new Bishop(move[3], move[2], color);
                        break;
                    default:
                }
            } else {
                data[move[3]][move[2]] = new Queen(move[3], move[2], color);
            }
        }
    }

    /**
     * 
     * @param color color of the player
     * @param columnNew column of the position the rook is trying to move to 
     * @return returns true is the rook and king can castle, false otherwise
     */
    public boolean canCastle(int color, int columnNew){
        //if King of color is in check, return false
        if(isKingInCheck(color)){
            return false;
        }
        //if King or Rooks of color have moved return false
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(data[i][j] instanceof King){
                    if(data[i][j].color == color){
                        King k = (King) data[i][j];
                        if(k.hasMoved == true){
                            return false;
                        }
                        
                    }
                }
                if(data[i][j] instanceof Rook){
                    if(data[i][j].color == color){
                        Rook r = (Rook) data[i][j];
                        if(columnNew == 6){
                            if(r.hasMoved == true && r.originalSpot.get(0)[1] == 7){
                                return false;
                            }
                        }
                        if(columnNew == 2){
                            if(r.hasMoved == true && r.originalSpot.get(0)[1] == 0){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        //figure out if King will pass through check while castling
        if(color == 0){
            if(columnNew == 6){
                for(int i = 4; i<8; i++){
                    if(data[0][i] instanceof Piece){
                        if(data[0][i].color != color){
                            return false;
                        } else{
                            if(data[0][i] instanceof Rook || data[0][i] instanceof King){
                                continue;
                            } else{
                                return false;
                            }
                        }
                    }
                }
            }
            if(columnNew == 2){
                for(int i = 0; i<4; i++){
                    if(data[0][i] instanceof Piece){
                        if(data[0][i].color != color){
                            return false;
                        } else{
                            if(data[0][i] instanceof Rook || data[0][i] instanceof King){
                                continue;
                            } else{
                                return false;
                            }
                        }
                    }
                }
            }
        }
        if(color == 1){
            if(columnNew == 6){
                for(int i = 4; i<8; i++){
                    if(data[7][i] instanceof Piece){
                        if(data[7][i].color != color){
                            return false;
                        } else{
                            if(data[7][i] instanceof Rook || data[7][i] instanceof King){
                                continue;
                            } else{
                                return false;
                            }
                        }
                    }
                }
            }
            if(columnNew == 2){
                for(int i = 0; i<4; i++){
                    if(data[7][i] instanceof Piece){
                        if(data[7][i].color != color){
                            return false;
                        } else{
                            if(data[7][i] instanceof Rook || data[7][i] instanceof King){
                                continue;
                            } else{
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 
     * @param color color of the player
     * @return returns true is the players king is in check, false otherwise
     */
    public boolean isKingInCheck(int color){

        // looping through board to find position of King
        int kingsRow = 0;
        int kingsColumn = 0;
        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                if(data[i][j] instanceof King){
                    if(data[i][j].color == color){
                        kingsRow = i;
                        kingsColumn = j;
                        break;
                    }
                }
            }
        }

        // checking each piece on the board and seeing if the King is in check or not
        // if a piece puts the king in check, return true

        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                if(data[i][j] instanceof Piece){
                    ArrayList<int[]> moves = data[i][j].findAvaiableMoves(i, j, data);
                    for(int[] k : moves){
                        if(k[0] == kingsRow && k[1] == kingsColumn){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * 
     * @param color color of the player
     * @param board the boards data field
     * @return true is the king is still in check after move has been made, false otherwise
     */
    public boolean isKingStillInCheck(int color, Piece[][] board){

        // looping through board to find position of King

        int kingsRow = 0;
        int kingsColumn = 0;
        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                if(board[i][j] instanceof King){
                    if(board[i][j].color == color){
                        kingsRow = i;
                        kingsColumn = j;
                        break;
                    }
                }
            }
        }

        // checking each piece on the board and seeing if the King is in check or not
        // if a piece puts the king in check, return true

        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                if(board[i][j] instanceof Piece){
                    ArrayList<int[]> moves = board[i][j].findAvaiableMoves(i, j, board);
                    for(int[] k : moves){
                        if(k[0] == kingsRow && k[1] == kingsColumn){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param color color of the player
     * @return returns true is players King is in checkmate, false otherwise
     */
    public boolean checkMate(int color){
        // looping through board to find position of King

        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                if(data[i][j] instanceof Piece){
                    if(data[i][j].color == color){
                        ArrayList<int[]> moves = data[i][j].findAvaiableMoves(i, j, data);
                        for(int[] k : moves){
                            Piece[][] temporaryBoard = new Piece[8][8];
                            for(int m = 0; m<ROWS; m++){
                                for(int n = 0; n<COLUMNS; n++){
                                    temporaryBoard[m][n] = data[m][n];
                                }
                            }

                            temporaryBoard[k[0]][k[1]] = data[i][j];
                            temporaryBoard[i][j] = null;

                            if(!isKingStillInCheck(color, temporaryBoard)){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Reset's pawns hasMoved2 field for en passant checking conditions
     * @param color color of the player
     */
    public void resetPawns(int color){
        for(int i =0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                if(data[i][j] instanceof Pawn){
                    Pawn p = (Pawn) data[i][j];
                    if(p.color == color){
                        p.hasMoved2 = false;
                    }
                }
            }
        }
    }

}
