package cisc181.cp_2;

/**
 * The only Action that can be taken by a player in TicTacToe5x5.
 * This Action will update the game state by placing a piece
 * of player's symbol at the given row and column.
 *  
 * @author jatlas
 */
public class PlacePieceAction implements Action<TicTacToeTypeGame> {
    private char player;
    private int row;
    private int column;
    
    public PlacePieceAction(char player, int row, int column) {
        this.player = player;
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getPlayer(){return player;}
    
    /**
     * Placing a piece is only valid if:
     * 1. It is within the bounds of the board
     * 2. and it is the player's turn
     * 3. and it is the "empty" piece at the given row/column
    */
    public boolean isValid(TicTacToeTypeGame game) {
        return game.getGameBoard().isInBounds(row, column) &&
                game.getTurnSymbol() == player &&
                game.getGameBoard().getPiece(row,column).isEmpty();
    }
    
    public void update(TicTacToeTypeGame game) {
        game.setPiece(row, column, new GamePiece(player));
        game.changeTurn();
    }

    public String toString() {
        return "PlacePiece: "+ player +" at "+ row +"," +column;
    }
}
