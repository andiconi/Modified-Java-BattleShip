package cisc181.cp_2;
import java.util.Scanner;


public class TicTacTossUI implements UserInput<TicTacToeTypeGame> {

    private TicTacToeTypeGame game;

    public TicTacTossUI(){
        game = new TicTacToeTypeGame('X','O',3,3,3);
    }

    public TicTacToeTypeGame getGame(){
        return game;
    }

    public TossPieceAction getUserNextMove(TicTacToeTypeGame game){

        Scanner scr = new Scanner(System.in);

        boolean validMove = false;
        TossPieceAction move = null;
        int row;
        int col;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter T to toss");
            String userResponse = scr.next();
            if (userResponse.equals("T") ) {
                // Create an instance of the corresponding action
                //  when user tosses their gamepiece, it will randomly land on the board
                row = (int) (Math.random() * (game.getGameBoard().getBoard().length));
                col = (int) (Math.random() * (game.getGameBoard().getBoard().length));
                move = new TossPieceAction(game.getTurnSymbol(), row, col);
                validMove = move.isValid(game);
            }
            // check if its a valid move
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        return move;
    }

    public static void main(String args[]){

        TicTacTossUI ttt = new TicTacTossUI();
        boolean gameEnded = false;
        TossPieceAction nextMove;

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
