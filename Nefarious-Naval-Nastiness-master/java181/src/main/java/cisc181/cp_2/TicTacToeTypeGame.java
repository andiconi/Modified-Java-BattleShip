package cisc181.cp_2;


public class TicTacToeTypeGame extends Game {

    private GameBoard gameBoard;
    // represents whose turn it is
    private char turnSymbol;
    // represents whose turn it is not
    private char notTurnSymbol;
    private int numConsecToWin;

    public TicTacToeTypeGame(char turnSymbol, char notTurnSymbol,int numRows, int numCols, int numConsecToWin){
        this.gameBoard = new GameBoard(setUpEmptyArray(numRows,numCols));
        this.turnSymbol = turnSymbol;
        this.notTurnSymbol = notTurnSymbol;
        this.numConsecToWin = numConsecToWin;
    }

    public GameBoard getGameBoard(){
        return  gameBoard;
    }

    public char getTurnSymbol(){
        return  turnSymbol;
    }

    public char getNotTurnSymbol(){
        return  notTurnSymbol;
    }

    public void changeTurn() {
        char current = turnSymbol;
        turnSymbol = notTurnSymbol;
        notTurnSymbol = current;
    }

    public void setPiece(int row, int column, GamePiece piece) {
        gameBoard.setPiece(row,column,piece);
    }

    public boolean isEnd() {
       return !gameBoard.hasEmptySpace() || isWinner(turnSymbol) || isWinner(notTurnSymbol);
    }

    // Player wins if num consec in a row
    public boolean isWinner(char symbol) {
        return getScore(symbol) == numConsecToWin;
    }

    public int getScore(char symbol){
        return gameBoard.getMaxConsec(symbol);
    }

    public String getStatus() {
        if (isEnd()) {
            if (isWinner(turnSymbol)) {
                return "\nPlayer " + getTurnSymbol() + " wins!\n";
            }
            else if (isWinner(getNotTurnSymbol())) {
                return "\nPlayer " + getNotTurnSymbol() + " wins!\n";
            }
            else {
                return "\nIt is a draw.\n";
            }
        }
        else {
            return "\nPlayer " + turnSymbol + "'s turn\n";
        }
    }

    public static GamePiece[][] setUpEmptyArray(int numRows, int numCols){

        // create a 2D array that will hold all the game pieces
        GamePiece[][]  arrayOfEmpty = new GamePiece[numRows][numCols];
        // Fill the array with empty pieces
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                arrayOfEmpty[row][col] = new GamePiece(GamePiece.EMPTY);
            }
        }
        return arrayOfEmpty;
    }

    @Override
    public String toString() {
        String status =   gameBoard.toString();
        status += "\n" + getStatus();
        return status;
    }
}

