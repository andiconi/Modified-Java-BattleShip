package edu.udel.abrevett.nnnastiness;

import java.util.Scanner;

import cisc181.cp_2.BattleShipGame;
import cisc181.cp_2.HitBoardAction;
import cisc181.cp_2.PlaceShipAction;
import cisc181.cp_2.Ship;
import cisc181.cp_2.UserInput;

public class BattleShipManager implements UserInput<BattleShipGame> {

    public final Ship Carrier,BattleShip,Cruiser,Submarine,Destroyer;
    private BattleShipGame game;
    private Scanner scr;
    private String input;

    public BattleShipManager(){
        this.game = new BattleShipGame();
        this.scr = new Scanner(this.input);

        Carrier = new Ship(5, 'C', "Carrier");
        BattleShip = new Ship(4, 'B', "BattleShip");
        Cruiser = new Ship(3, 'R', "Crusier");
        Submarine = new Ship(3, 'S', "Submarine");
        Destroyer = new Ship(2, 'D', "Destroyer");
    }

    public BattleShipGame getGame() {
        return game;
    }
    public PlaceShipAction getUserCruiser(BattleShipGame game){
        PlaceShipAction placeCruiser;
        int row,col;
        String direction;
        this.input = input;

        // Scans the next three fields in input String (int,int,String)
        row = this.scr.nextInt();
        col = this.scr.nextInt();
        direction = this.scr.next();
        // Create an instance of the corresponding action
        placeCruiser = new PlaceShipAction(new Ship(3, 'R', "Crusier"),game.getTurnSymbol(),row,col,direction);

        // return the move action
        return placeCruiser;
    }
    public PlaceShipAction getUserCarrier(BattleShipGame game){
        PlaceShipAction placeCarrier;
        int row;
        int col;
        String direction;
        this.input = input;

        // Get the user input for the next move
        row = this.scr.nextInt();
        col = this.scr.nextInt();
        direction = this.scr.next();
        // Create an instance of the corresponding action
        placeCarrier = new PlaceShipAction(new Ship(5, 'C', "Carrier"),game.getTurnSymbol(), row,col, direction );

        // return the move
        return placeCarrier;
    }
    public PlaceShipAction getUserBattleShip(BattleShipGame game){
        PlaceShipAction placeBattleShip;
        int row;
        int col;
        String direction;
        this.input = input;


        // Get the user input for the next move
        row = this.scr.nextInt();
        col = this.scr.nextInt();
        direction = this.scr.next();
        // Create an instance of the corresponding action
        placeBattleShip = new PlaceShipAction(new Ship(4, 'B', "BattleShip"),game.getTurnSymbol(), row,col, direction );

        // return the move
        return placeBattleShip;
    }
    public PlaceShipAction getUserSubmarine(BattleShipGame game){
        PlaceShipAction placeSubmarine;
        int row;
        int col;
        String direction;
        this.input = input;

        // Get the user input for the next move
        row = this.scr.nextInt();
        col = this.scr.nextInt();
        direction = this.scr.next();
        // Create an instance of the corresponding action
        placeSubmarine = new PlaceShipAction(new Ship(3, 'S', "Submarine"),game.getTurnSymbol(), row,col, direction );

        // return the move
        return placeSubmarine;
    }
    public PlaceShipAction getUserDestroyer(BattleShipGame game){
        PlaceShipAction placeDestroyer = null;
        int row,col;
        String direction;
        this.input = input;

        // Get the user input for the next move
        row = this.scr.nextInt();
        col = this.scr.nextInt();
        direction = this.scr.next();
        // Create an instance of the corresponding action
        placeDestroyer = new PlaceShipAction(new Ship(2, 'D', "Destroyer"),game.getTurnSymbol(), row,col, direction );

        // return the move
        return placeDestroyer;
    }
    public HitBoardAction getUserNextMove(BattleShipGame game){
        //Scanner scr = new Scanner(System.in);
        boolean validMove = false;
        HitBoardAction fire = null;
        int row;
        int col;

        while (!validMove){
            // Get the user input for the next move
            System.out.println("Enter row index of your Shot:");
            row = this.scr.nextInt();
            System.out.println("Enter column index of your Shot:");
            col = this.scr.nextInt();
            // Create an instance of the corresponding action
            fire = new HitBoardAction(game.getTurnSymbol(), row,col);
            // check if its a valid move
            validMove = fire.isValid(game);
            if(!validMove){
                System.out.println("Invalid Move - try again");
            }
        }
        // return the move
        return fire;
    }
}
