package cisc181.cp_2;

import java.util.Scanner;

public class BattleShipUI implements UserInput<BattleShipGame> {

    Ship Carrier = new Ship(5, 'C', "Carrier");
    Ship BattleShip = new Ship(4, 'B', "BattleShip");
    Ship Cruiser = new Ship(3, 'R', "Crusier");
    Ship Submarine = new Ship(3, 'S', "Submarine");
    Ship Destroyer = new Ship(2, 'D', "Destroyer");
    private BattleShipGame game;

    public BattleShipUI(){
        game = new BattleShipGame();
    }

    public BattleShipGame getGame() {
        return game;
    }
    public PlaceShipAction getUserCruiser(BattleShipGame game){
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        PlaceShipAction placeCruiser = null;
        int row;
        int col;
        String direction;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your Cruiser:");
            row = scr.nextInt();
            System.out.println("Enter column index of your Cruiser:");
            col = scr.nextInt();
            System.out.println("Enter direction of your Cruiser:");
            direction = scr.next();
            // Create an instance of the corresponding action
            placeCruiser = new PlaceShipAction(new Ship(3, 'R', "Crusier"),game.getTurnSymbol(), row,col, direction );
            // check if its a valid move
            validMove = placeCruiser.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return placeCruiser;
    }
    public PlaceShipAction getUserCarrier(BattleShipGame game){
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        PlaceShipAction placeCarrier = null;
        int row;
        int col;
        String direction;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your Carrier:");
            row = scr.nextInt();
            System.out.println("Enter column index of your Carrier:");
            col = scr.nextInt();
            System.out.println("Enter direction of your Carrier:");
            direction = scr.next();
            // Create an instance of the corresponding action
            placeCarrier = new PlaceShipAction(new Ship(5, 'C', "Carrier"),game.getTurnSymbol(), row,col, direction );
            // check if its a valid move
            validMove = placeCarrier.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return placeCarrier;
    }
    public PlaceShipAction getUserBattleShip(BattleShipGame game){
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        PlaceShipAction placeBattleShip = null;
        int row;
        int col;
        String direction;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your BattleShip:");
            row = scr.nextInt();
            System.out.println("Enter column index of your BattleShip:");
            col = scr.nextInt();
            System.out.println("Enter direction of your BattleShip:");
            direction = scr.next();
            // Create an instance of the corresponding action
            placeBattleShip = new PlaceShipAction(new Ship(4, 'B', "BattleShip"),game.getTurnSymbol(), row,col, direction );
            // check if its a valid move
            validMove = placeBattleShip.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return placeBattleShip;
    }
    public PlaceShipAction getUserSubmarine(BattleShipGame game){
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        PlaceShipAction placeSubmarine = null;
        int row;
        int col;
        String direction;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your Submarine:");
            row = scr.nextInt();
            System.out.println("Enter column index of your Submarine:");
            col = scr.nextInt();
            System.out.println("Enter direction of your Submarine:");
            direction = scr.next();
            // Create an instance of the corresponding action
            placeSubmarine = new PlaceShipAction(new Ship(3, 'S', "Submarine"),game.getTurnSymbol(), row,col, direction );
            // check if its a valid move
            validMove = placeSubmarine.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return placeSubmarine;
    }
    public PlaceShipAction getUserDestroyer(BattleShipGame game){
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        PlaceShipAction placeDestroyer = null;
        int row;
        int col;
        String direction;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your Destroyer:");
            row = scr.nextInt();
            System.out.println("Enter column index of your Destroyer:");
            col = scr.nextInt();
            System.out.println("Enter direction of your Destroyer:");
            direction = scr.next();
            // Create an instance of the corresponding action
            placeDestroyer = new PlaceShipAction(new Ship(2, 'D', "Destroyer"),game.getTurnSymbol(), row,col, direction );
            // check if its a valid move
            validMove = placeDestroyer.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return placeDestroyer;
    }
    public HitBoardAction getUserNextMove(BattleShipGame game) {
        Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        HitBoardAction fire = null;
        int row;
        int col;

        while (!validMove) {
            // Get the user input for the next move
            System.out.println("Enter row index of your Shot:");
            row = scr.nextInt();
            System.out.println("Enter column index of your Shot:");
            col = scr.nextInt();
            // Create an instance of the corresponding action
            fire = new HitBoardAction(game.getTurnSymbol(), row, col);
            // check if its a valid move
            validMove = fire.isValid(game);
            if (!validMove) {
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return fire;
    }

    public static void main(String args[]) throws InterruptedException {

        BattleShipUI BSG = new BattleShipUI();
        boolean gameEnded = false;

        BSG.getGame().performAction(BSG.getUserCarrier(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserBattleShip(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserCruiser(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserSubmarine(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserDestroyer(BSG.getGame()));
        BSG.getGame().changeTurn();
        BSG.getGame().performAction(BSG.getUserCarrier(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserBattleShip(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserCruiser(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserSubmarine(BSG.getGame()));
        BSG.getGame().performAction(BSG.getUserDestroyer(BSG.getGame()));
        BSG.getGame().changeTurn();

        HitBoardAction nextMove;

        while (!BSG.getGame().isEnd()){
            // print status of game
            System.out.println(BSG.getGame());
            // get the next move from the user
            nextMove = BSG.getUserNextMove(BSG.getGame());
            // print the next move
            System.out.println(nextMove);
            // perform the next move
            BSG.getGame().performAction(nextMove);
            System.out.println("your score is:" + BSG.getGame().getScore(BSG.getGame().getNotTurnSymbol()));
            Thread.sleep(3000);
        }
        System.out.println(BSG.getGame());
    }

}
