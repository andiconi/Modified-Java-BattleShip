package edu.udel.abrevett.nnnastiness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Scanner;

import cisc181.cp_2.BattleShipGame;
import cisc181.cp_2.GameBoard;
import cisc181.cp_2.HitBoardAction;
import cisc181.cp_2.PlaceShipAction;
import cisc181.cp_2.Ship;


public class GameBoardActivity extends AppCompatActivity implements View.OnClickListener {
    private BattleShipGame game;
    private String direction, gameState;
    private int p1shipcount,p2shipcount;

    //Ship Definitions


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message );

        //Get Intent data
        Intent intent = getIntent();

        this.game = new BattleShipGame();
        this.direction = "right";
        this.gameState = "setup";
        this.p1shipcount = 0;
        this.p2shipcount = 0;


        //Registers all grid ImageButtons for OnClickListener
        for(int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int id = getResources().getIdentifier("row" + row + "c" + col, "id", getPackageName());
                ImageButton imgbtnCoord = findViewById(id);
                imgbtnCoord.setOnClickListener(this);
                imgbtnCoord.setTag(new ButtonData(row,col,"grid"));
                imgbtnCoord.setBackgroundResource(R.color.colorPrimary);
            }
        }

        Button btnSwap = findViewById(R.id.btnSwap);
        btnSwap.setOnClickListener(this);
        btnSwap.setTag(new ButtonData(-1,-1,"swap"));
    }

    public void onClick(View v){
        ButtonData data = (ButtonData) v.getTag();

        if(!this.game.isEnd()) {
            if(data.getData().equals("grid")) {
                PlaceShipAction placeAction;
                if(p1shipcount == 5 && p2shipcount == 5) {
                    HitBoardAction hitAction = new HitBoardAction(game.getTurnSymbol(), data.getRow(), data.getCol());
                    if (hitAction.isValid(this.game)) {
                        game.performAction(hitAction);
                        System.out.println(hitAction);
                    } else {
                        Toast clickInd = Toast.makeText(this, "Invalid Hit Operation", Toast.LENGTH_SHORT);
                        clickInd.show();
                    }
                }
                else if(p1shipcount == 5){
                    switch (p2shipcount){
                        case 0:
                            placeAction = new PlaceShipAction(new Ship(5, 'C', "Carrier"),game.getTurnSymbol(),data.getRow(), data.getCol(),this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p2shipcount++;
                            }
                            break;
                        case 1:
                            placeAction = new PlaceShipAction(new Ship(4, 'B', "BattleShip"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p2shipcount++;
                            }
                            break;
                        case 2:
                            placeAction = new PlaceShipAction(new Ship(3, 'S', "Submarine"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p2shipcount++;
                            }
                            break;
                        case 3:
                            placeAction = new PlaceShipAction(new Ship(3, 'R', "Crusier"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p2shipcount++;
                            }
                            break;
                        case 4:
                            placeAction = new PlaceShipAction(new Ship(2, 'D', "Destroyer"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p2shipcount++;
                                gameState = "play";
                                this.game.changeTurn();
                            }
                            break;
                        default:
                            break;
                    }
                }
                else{
                    switch (p1shipcount){
                        case 0:
                            placeAction = new PlaceShipAction(new Ship(5, 'C', "Carrier"),game.getTurnSymbol(),data.getRow(), data.getCol(),this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p1shipcount++;
                            }
                            break;
                        case 1:
                            placeAction = new PlaceShipAction(new Ship(4, 'B', "BattleShip"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p1shipcount++;
                            }
                            break;
                        case 2:
                            placeAction = new PlaceShipAction(new Ship(3, 'S', "Submarine"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p1shipcount++;
                            }
                            break;
                        case 3:
                            placeAction = new PlaceShipAction(new Ship(3, 'R', "Crusier"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p1shipcount++;
                            }
                            break;
                        case 4:
                            placeAction = new PlaceShipAction(new Ship(2, 'D', "Destroyer"), game.getTurnSymbol(), data.getRow(), data.getCol(), this.direction);
                            if(placeAction.isValid(this.game)){
                                game.performAction(placeAction);
                                p1shipcount++;
                                this.game.changeTurn();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

            if(data.getData().equals("swap")){
                if(this.direction.equals("up")){ this.direction = "right";}
                else if(this.direction.equals("right")){this.direction = "down";}
                else if(this.direction.equals("down")){this.direction = "left";}
                else{this.direction = "up";}
            }
        }
        updateViews(game);

        Toast clickInd = Toast.makeText(this,"You have clicked the Activity", Toast.LENGTH_SHORT);
        clickInd.show();
    }

    //Modified Helper Methods from BattleShipUI


    //Helper methods from examples

    public void updateViews(BattleShipGame game){
        if(gameState.equals("setup")){
            GameBoard ships = game.getShipBoard();
            for(int row = 0; row < 10; row++){
                for(int col = 0; col < 10; col++){
                    int id = getResources().getIdentifier("row" + row + "c" + col, "id", getPackageName());
                    ImageButton imgbtnCoord = findViewById(id);
                    if(!ships.getPiece(row,col).isEmpty()) {
                        imgbtnCoord.setBackgroundResource(R.color.colorAccent);
                    }
                    else{
                        imgbtnCoord.setBackgroundResource(R.color.colorPrimary);
                    }

                }
            }
        }
        else{
            GameBoard ships = game.getGameBoard();
            for(int row = 0; row < 10; row++){
                for(int col = 0; col < 10; col++){
                    int id = getResources().getIdentifier("row" + row + "c" + col, "id", getPackageName());
                    ImageButton imgbtnCoord = findViewById(id);
                    if(!ships.getPiece(row,col).isEmpty()) {
                        imgbtnCoord.setBackgroundResource(R.color.colorPrimaryDark);
                    }
                    else{
                        imgbtnCoord.setBackgroundResource(R.color.colorPrimary);
                    }
                }
            }
        }
    }
}
