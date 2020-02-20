package cisc181.cp_2;
import java.util.Scanner;


public class TicTacToeUI implements UserInput<TicTacToeTypeGame>{

    private TicTacToeTypeGame game;

    public TicTacToeUI(){
        game = new TicTacToeTypeGame('X','O',3,3,3);
    }

    public TicTacToeTypeGame getGame(){
        return game;
    }

    public PlacePieceAction getUserNextMove(TicTacToeTypeGame game){
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        PlacePieceAction move = null;
        int row;
        int col;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your next move:");
            row = scr.nextInt();
            System.out.println("Enter column index of your next move:");
            col = scr.nextInt();
            // Create an instance of the corresponding action
            move = new PlacePieceAction(game.getTurnSymbol(),row,col);
            // check if its a valid move
            validMove = move.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return move;
    }

    public static void main(String args[]){

        TicTacToeUI ttt = new TicTacToeUI();
        boolean gameEnded = false;
        PlacePieceAction nextMove;

        while (!ttt.getGame().isEnd()){
            // print status of game
            System.out.println(ttt.getGame());
            // get the next move from the user
            nextMove = ttt.getUserNextMove(ttt.getGame());
            // print the next move
            System.out.println(nextMove);
            // perform the next move
            ttt.getGame().performAction(nextMove);
        }
        System.out.println(ttt.getGame());
    }
}
