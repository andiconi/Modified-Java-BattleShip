package cisc181.cp_2;

public class BattleShipGame extends Game {
    // these game boards are the one the players see
    private GameBoard p1Gameboard;
    private GameBoard p2Gameboard;

    // these game boards are the one the computer uses to detect hits
    private GameBoard p1Shipboard;
    private GameBoard p2Shipboard;

    private int flagCountX = 5;
    //player 1 is blue player 2 is red
    private int flagCountY = 5;
    // when flag is captured add 1 to flag count
    private char turnSymbol;
    private char notTurnSymbol;
    public BattleShipGame(){
        this.p1Gameboard = new GameBoard(setUpEmptyArray(10,10));
        this.p2Gameboard = new GameBoard(setUpEmptyArray(10,10));
        this.p1Shipboard = new GameBoard(setUpEmptyArray(10,10));
        this.p2Shipboard = new GameBoard(setUpEmptyArray(10,10));
        this.turnSymbol = 'X';
        this.notTurnSymbol = 'Y';

    }

    public int getFlagCountX() {
        return flagCountX;
    }

    public GameBoard getP1Shipboard() {
        return p1Shipboard;
    }

    public GameBoard getP2Shipboard() {
        return p2Shipboard;
    }
    public GameBoard getShipBoard() {
        char board2Get = this.getTurnSymbol();
        if(board2Get == 'X'){
            return this.getP1Shipboard();
        }
        else return this.getP2Shipboard();
    }

    public int getFlagCountY() {
        return flagCountY;
    }
    public int getFlagCount(char symbol){
        if (symbol == 'X'){
            return getFlagCountX();
        }
        else if (symbol == 'Y'){
            return getFlagCountY();
        }
        else return 0;
    }

    public void setFlagCountY(int flagCountY) {
        this.flagCountY = flagCountY;
    }

    public GameBoard getP2Gameboard() {
        return p2Gameboard;
    }
    public void setPiece(int row, int column, GamePiece piece) {
        getShipBoard().setPiece(row,column,piece);
    }
    public void setPlayerPiece(int row, int column, GamePiece piece) {
        getGameBoard().setPiece(row,column,piece);
    }



    public GameBoard getP1Gameboard() {
        return p1Gameboard;
    }

    public GameBoard getGameBoard() {
        char board2Get = this.getTurnSymbol();
        if(board2Get == 'X'){
            return this.getP1Gameboard();
        }
        else return this.getP2Gameboard();
    }
    public void setFlagcount(){
        char player = this.getTurnSymbol();
        if(player == 'X'){
            this.flagCountX++;
            this.flagCountY--;
        }
        else{
            this.flagCountY++;
            this.flagCountX--;
        }

    }

    public void setFlagCountX(int flagCountX) {
        this.flagCountX = flagCountX;
    }


    /**
     * Returns a String representation of the current two Gameboards
     * @return String - the two Gameboards, the status, and the turn.
     */
    public static GamePiece [][] setUpEmptyArray(int numRows, int numCols){

        // create a 2D array that will hold all the game pieces
        GamePiece [][]  arrayOfEmpty = new GamePiece[numRows][numCols];
        // Fill the array with empty pieces
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                arrayOfEmpty[row][col] = new GamePiece(GamePiece.EMPTY);
            }
        }
        return arrayOfEmpty;
    }

    public char getNotTurnSymbol() {
        return notTurnSymbol;
    }

    public char getTurnSymbol() {
        return turnSymbol;
    }
    public void changeTurn() {
        char current = turnSymbol;
        turnSymbol = notTurnSymbol;
        notTurnSymbol = current;
    }

    public int getScore(char symbol){
            if(symbol == 'X'){
                return getFlagCountX();
            }
            else if (symbol == 'Y'){
                return getFlagCountY();
            }
            else return 0;

    }

    public boolean isWinner(char symbol){
            if (getFlagCountY() == 0 && symbol == 'X'){
                return true;
            }
            else if (getFlagCountX() == 0 && symbol == 'Y'){
                return true;
            }
            else return false;
    }

    public String getStatus(){
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
    public boolean isShipPieceDown(int row, int col){
        if(this.getGameBoard().getPiece(row, col).getSymbol() == 'H' || this.getGameBoard().getPiece(row, col).getSymbol() == 'Q'){
            return true;
        }
        else return false;
    }
    public boolean isShipDestroyed(Ship ship){
        int downcount = 0;
        for(int i = 0; i < this.getGameBoard().getBoard().length; i++){
            for(int j = 0; j < this.getGameBoard().getBoard()[1].length; j++){
                if((this.getShipBoard().getPiece(i,j).getSymbol() == ship.getSymbol() || this.getShipBoard().getPiece(i,j).getSymbol() == '#') && this.isShipPieceDown(i,j)){
                    downcount++;
                }
            }
        }
        if (ship.getLength() == downcount) {
            return true;
        }

        else {
            return false;

        }
    }
    public void destroyShip(Ship ship){
        for(int i = 0; i < this.getGameBoard().getBoard().length; i++){
            for(int j = 0; j < this.getGameBoard().getBoard()[1].length; j++){
                if(isShipDestroyed(ship)){
                    if(this.getGameBoard().getPiece(i, j).getSymbol() == 'H') {
                        this.getGameBoard().setPiece(i, j, new GamePiece('Q'));
                    }
                 }
            }

            }
    }

    public Ship charToShip(char symbol){

        if (symbol == 'C'){
            return new Ship(5, 'C', "Carrier");
        }
        else if (symbol == 'B'){
            return new Ship(4, 'B', "BattleShip");
        }
        else if (symbol =='R'){
            return new Ship(3, 'R', "Crusier");
        }
        else if (symbol == 'S'){
            return new Ship(3, 'S', "Submarine");
        }
        else if (symbol == 'D'){
            return new Ship(2, 'D', "Destroyer");
        }
        else return new Ship(2, 'D', "Destroyer");
    }

    /*
     * OVERRIDE BLOCK
     *
     * Put any @Override functions below
     */

    /**
     * Determines if the game is over or not depending on the current amount of leftover flags
     *
     * @return boolean - if the game is over
     */
    @Override
    public String toString() {

        String status =   getGameBoard().toString();
        status += "\n" + getStatus();
        return status;
    }
    @Override
    public boolean isEnd() {
        if (this.getFlagCountX() == 0 || this.getFlagCountY() == 0){
            return true;
        }
        else return false;
    }

}
