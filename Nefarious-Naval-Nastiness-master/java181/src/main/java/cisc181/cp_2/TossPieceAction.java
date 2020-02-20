package cisc181.cp_2;

public class TossPieceAction implements Action<TicTacToeTypeGame> {
    private char player;
    private int row;
    private int column;

    public TossPieceAction (char player, int row, int column) {
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
     */
    public boolean isValid(TicTacToeTypeGame game) {
        return game.getGameBoard().isInBounds(row, column) &&
                game.getTurnSymbol() == player ;
    }

    public void update(TicTacToeTypeGame game) {
        game.setPiece(row, column, new GamePiece(player));
        game.changeTurn();
    }

    public String toString() {
        return "PlacePiece: "+ player +" at "+ row +"," +column;
    }

}
