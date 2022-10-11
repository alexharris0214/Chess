package game;
import board.*;
import pieces.Pawn;
import pieces.Piece;

import java.util.Scanner;

/**
 * @author David Katri
 * @author Alex Harris
 * 
 * Chess class contains main game loop
*/
public class Chess {
    public static void main(String[] args) {
        Board board = new Board();
        boolean run = true;
        int turn = 1;
        Scanner sc = new Scanner(System.in);
        boolean mustDraw = false;
        boolean firstRun = true;

        // Main game loop
        while(run){
            //if the first run, call drawBoard(true) to assign original spots of Rooks
            if(firstRun == true){
                board.drawBoard(true);
            }
            firstRun = false;
            //Whites turn
            if(turn == 1){
                // Checking to see if Blacks turn put White in check
                if(board.isKingInCheck(turn)){
                    System.out.println("White is in Check");
                    if(board.checkMate(turn)){
                        System.out.println("Checkmate, Black Wins!");
                        run = false;
                        continue;
                    }
                }
                board.resetPawns(turn);
                System.out.print("White's Move: ");
                String entry = sc.nextLine();

                if(mustDraw){
                    if(entry.equals("draw")){
                        run = false;
                        System.out.println("The game ended in a draw");
                        continue;
                    }
                    else{
                        System.out.println("You are obligated to draw (enter 'draw').");
                        continue; 
                    }
                }

                if(entry.equals("q")){
                    run = false;
                    continue;
                }

                if(entry.equals("resign")){
                    run = false;
                    System.out.println("Black Wins!");
                    continue;
                }

                if(entry.contains("draw?")){
                    turn = 0;
                    mustDraw = true;
                    continue;
                }
                if(board.canPromote(turn)){
                    board.handlePromote(entry, turn);
                    turn = 0;
                    continue;
                }

            
                if(!mustDraw){
                    // Piece move handler
                    int[] move =  parseMove(entry);
                    Piece oldPiece = board.data[move[1]][move[0]];
                    Piece oldPiece2 = board.data[move[3]][move[2]];
                    if(board.move(move[1], move[0], move[3], move[2], turn)){
                        if(board.isKingInCheck(turn)){
                            board.data[move[3]][move[2]] = oldPiece2;
                            board.data[move[1]][move[0]] = oldPiece;
                            System.out.println("Illegal Move, try again");
                            continue;
                        }
                        System.out.println();
                        // checking to see if en pasant was played
                        try{
                            if(board.data[move[3]][move[2]] instanceof Pawn){
                                if(board.data[move[3]+1][move[2]] instanceof Pawn){
                                    if(board.data[move[3]+1][move[2]].color != turn){
                                        board.data[move[3]+1][move[2]] = null;
                                    }
                                }
                            }
                        } catch(ArrayIndexOutOfBoundsException e){
                            // do nothing
                        }
                        board.drawBoard(firstRun);
                        turn = 0;
                    } else {
                        System.out.println("Illegal Move, try again");
                        continue;
                    }
                }

            // Blacks turn
            } else {
                // Checking to see if Whites turn put Black in check
                if(board.isKingInCheck(turn)){
                    System.out.println("Black is in check");
                    if(board.checkMate(turn)){
                        System.out.println("Checkmate, White Wins!");
                        run = false;
                        continue;
                    }
                }
                board.resetPawns(turn);
                System.out.print("Black's Move: ");
                String entry = sc.nextLine();

                if(mustDraw){
                    System.out.println("You are obligated to draw (enter 'draw').");
                    if(entry.equals("draw")){
                        run = false;
                        System.out.println("The game ended in a draw");
                        continue;
                    }
                    else{
                        continue; 
                    }
                }
                if(entry.equals("q")){
                    run = false;
                    continue;
                }

                if(entry.equals("resign")){
                    run = false;
                    System.out.println("White Wins!");
                    continue;
                }

                if(entry.contains("draw?")){
                    turn = 1;
                    mustDraw = true;
                    continue;
                }

                if(board.canPromote(turn)){
                    board.handlePromote(entry, turn);
                    turn = 1;
                    continue;
                }

                if(!mustDraw){
                    // Piece move handler
                    int[] move =  parseMove(entry);
                    Piece oldPiece = board.data[move[1]][move[0]];
                    Piece oldPiece2 = board.data[move[3]][move[2]];
                    if(board.move(move[1], move[0], move[3], move[2], turn)){
                        // checking to see if move resulted in King being in check
                        // if true, illegal move and put piece back
                        if(board.isKingInCheck(turn)){
                            board.data[move[3]][move[2]] = oldPiece2;
                            board.data[move[1]][move[0]] = oldPiece;
                            System.out.println("Illegal Move, try again");
                            continue;
                        }

                        // checking to see if en pasant was played
                        try{
                            if(board.data[move[3]][move[2]] instanceof Pawn){
                                if(board.data[move[3]-1][move[2]] instanceof Pawn){
                                    if(board.data[move[3]-1][move[2]].color != turn){
                                        board.data[move[3]-1][move[2]] = null;
                                    }
                                }
                            }
                        } catch(ArrayIndexOutOfBoundsException e){
                            // do nothing
                        } 
                        System.out.println();
                        board.drawBoard(firstRun);
                        turn = 1;
                    } else {
                        System.out.println("Illegal Move, try again");
                        continue;
                    }
                }
            }
        }
        // Closing scanner before closing program
        sc.close();
    }
    
    /**
    * Parses the user input into variables that methods can use
    * @param move contains the entry that the user provided
    * @return returns the parsedmove into an array of size 4 containg the coordinates of the new and orignal move
    **/

    public static int[] parseMove(String move){
        int[] result = new int[4];
        int counter = 0;
        for(int i =0; i<move.length(); i++){
            char c = move.charAt(i);
            if(c!= ' '){
                switch(c){
                    case 'a':
                        result[counter] = 0;
                        break;
                    case 'b':
                        result[counter] = 1;
                        break;
                    case 'c':
                        result[counter] = 2;
                        break;
                    case 'd':
                        result[counter] = 3;
                        break;
                    case 'e':
                        result[counter] = 4;
                        break;
                    case 'f':
                        result[counter] = 5;
                        break;
                    case 'g':
                        result[counter] = 6;
                        break;
                    case 'h':
                        result[counter] = 7;
                        break;
                    // default case where we have a row instead of a column
                    // in which case we return the position in the grid where the respective number
                    // corresponds to
                    default:
                        result[counter] = 8 - Character.getNumericValue(c); // going to be 8-c, since top and bottom are switched
                }
                counter++;
            }
        }
        return result;
    }
}
