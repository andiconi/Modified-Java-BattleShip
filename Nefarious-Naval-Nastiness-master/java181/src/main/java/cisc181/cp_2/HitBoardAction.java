package cisc181.cp_2;

public class HitBoardAction implements Action<BattleShipGame> {
    Ship Carrier = new Ship(5, 'C', "Carrier");
    Ship BattleShip = new Ship(4, 'B', "BattleShip");
    Ship Cruiser = new Ship(3, 'R', "Crusier");
    Ship Submarine = new Ship(3, 'S', "Submarine");
    Ship Destroyer = new Ship(2, 'D', "Destroyer");
    private char player;
    private int row;
    private int column;
    private char[] validHits = new char[]{'C','B','R','S','D','#' };
    public HitBoardAction(char player, int row, int column){
        this.column = column;
        this.row =row;
        this.player = player;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public char getPlayer() {
        return player;
    }

    public static boolean contains(char c, char[] array) {
        for (char x : array) {
            if (x == c) {
                return true;
            }
        }
        return false;
    }
    public boolean isValid(BattleShipGame game) {
        return game.getGameBoard().isInBounds(getRow(), getColumn()) &&
                game.getTurnSymbol() == getPlayer() &&
                (game.getGameBoard().getPiece(getRow(), getColumn()).isEmpty() || contains(game.getGameBoard().getPiece(getRow(), getColumn()).getSymbol(), validHits));
    }

    public void update(BattleShipGame game){
        Ship shipToDestroy = game.charToShip(game.getShipBoard().getPiece(getRow(), getColumn()).getSymbol());
        if (game.getShipBoard().getPiece(getRow(), getColumn()).isEmpty()){
            game.setPlayerPiece(getRow(), getColumn(), new GamePiece('M'));
        }
        else {

            if (game.getShipBoard().getPiece(getRow(), getColumn()).getSymbol() == '#') {
                game.setPlayerPiece(getRow(), getColumn(), new GamePiece('H'));
                game.setFlagcount();
            }
            game.setPlayerPiece(getRow(), getColumn(), new GamePiece('H'));
        }
        if (game.isShipDestroyed(shipToDestroy)){
            game.destroyShip(shipToDestroy);
        }

        //Comment out for testing
        System.out.println(game.getGameBoard().toString());
        game.changeTurn();
    }

}
