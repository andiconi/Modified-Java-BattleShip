package cisc181.cp_2;
import java.util.concurrent.ThreadLocalRandom;

public class PlaceFlag {
    //did not make this an action class because the user does not activate it.
    BattleShipGame game;
    //helps get board
    private char player;
    private Flag flagToPlace;
    public PlaceFlag(char player, Flag flagToPlace, BattleShipGame game){
        this.flagToPlace = flagToPlace;
        this.player = player;
        this.game = game;
    }

    public char getPlayer() {
        return player;
    }

    public Flag getFlagToPlace() {
        return flagToPlace;
    }

    public BattleShipGame getGame() {
        return game;
    }

    public GameBoard getBoardShipOn() {
        if ( this.getPlayer() == 'X'){
            return this.getGame().getP1Shipboard();

        }
        else return this.getGame().getP2Shipboard();
    }

    public void update(Ship givenShip){
        int randomRow = ThreadLocalRandom.current().nextInt(0, 10);
        int randomCol = ThreadLocalRandom.current().nextInt(0, 10);
        if (!( getBoardShipOn().getPiece(randomRow, randomCol).getSymbol() == givenShip.getSymbol()))
            update(givenShip);
        else getBoardShipOn().setPiece(randomRow,randomCol, getFlagToPlace());



    }


}
