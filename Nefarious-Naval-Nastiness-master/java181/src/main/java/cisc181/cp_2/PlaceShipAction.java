package cisc181.cp_2;

public class PlaceShipAction implements Action<BattleShipGame> {
    private int lengthOfShip;
    private String type;
    private char player;
    private char symbol;
    private int row;
    private int column;
    private String direction;
    public PlaceShipAction(Ship type, char player, int row, int column, String direction) {
        this.lengthOfShip = type.getLength();
        this.symbol = type.getSymbol();
        this.player = player;
        this.type = type.getType();
        this.column = column;
        this.row = row;
        this.direction = direction.toLowerCase();
    }

    public String getDirection() {
        return direction;
    }

    public int getStartColumn(){
        return this.column;

    }
    public int getStartRow(){
        return this.row;

    }

    public char getPlayer() {
        return player;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getFinalColumn(){
        int fincolumn = 0;
        if (this.getDirection().equals("up") || this.getDirection().equals("down") ){
            fincolumn = getStartColumn();
        }
        else if (this.getDirection().equals("left")){
            fincolumn = getStartColumn() - this.lengthOfShip;
        }
        else if (this.getDirection().equals("right")) {
            fincolumn = getStartColumn() + this.lengthOfShip;
        }
        return fincolumn;


    }
    /*
    gets direction of ship and uses that to figure where it will end based on length of ship
     */
    public int getFinalRow(){
        int finRow = 0;
        if (this.getDirection().equals("left") || this.getDirection().equals("right") ){
            finRow = getStartRow();
        }
        else if (this.getDirection().equals("up")){
            finRow = getStartRow() - this.lengthOfShip;
        }
        else if (this.getDirection().equals("down")) {
            finRow = getStartRow() + this.lengthOfShip;
        }
        return finRow;

    }
    public boolean isValid(BattleShipGame game) {
        return game.getGameBoard().isInBounds(getStartRow(), getStartColumn()) &&
                game.getGameBoard().isInBounds(getFinalRow(), getFinalColumn()) &&
                game.getTurnSymbol() == player &&
                (this.amtSpacesClear(game) == lengthOfShip);
    }
    /*
    starts at start row and uses direction to place a ship piece in each space for length of ship.
     */

    public void update(BattleShipGame game) {
        Ship ship = new Ship(lengthOfShip, this.getSymbol(), this.type);
        Flag flag = new Flag(getFlagColor(game));
        if(this.getDirection().equals("up")){
            int loopRow = getStartRow();
            for (int i = 0; i < lengthOfShip; i++) {
                if (game.getGameBoard().getPiece(loopRow, getStartColumn()).getSymbol() == 'E') {
                    game.setPiece(loopRow, getStartColumn(),ship);
                }
                loopRow += -1;
            }
        }
        else if(this.getDirection().equals("down")){
            int loopRow = getStartRow();
            for (int i = 0; i < lengthOfShip; i++) {
                if (game.getGameBoard().getPiece(loopRow, getStartColumn()).getSymbol() == 'E') {
                    game.setPiece(loopRow, getStartColumn(), ship);
                }
                loopRow += 1;
            }
        }
        else if(this.getDirection().equals("left")){
            int loopColumn = getStartColumn();
            for (int i = 0; i < lengthOfShip; i++) {
                if (game.getGameBoard().getPiece(getStartRow(), loopColumn).getSymbol() == 'E') {
                    game.setPiece(getStartRow(), loopColumn,ship);
                }
                loopColumn += -1;
            }
        }
        else if(this.getDirection().equals("right")){
            int loopColumn = getStartColumn();
            for (int i = 0; i < lengthOfShip; i++) {
                if (game.getGameBoard().getPiece(getStartRow(), loopColumn).getSymbol() == 'E') {
                    game.setPiece(getStartRow(), loopColumn, ship);
                }
                loopColumn += 1;
            }
        }
        PlaceFlag shipFlag =  new PlaceFlag(this.getPlayer(), flag, game);
        shipFlag.update(ship);
    }
    /*
    starts at start row and uses direction to check if spaces in between start and finish of ship
    are clear and adds one to a counter where the spaces are empty.
     */
    public int amtSpacesClear(BattleShipGame game){
        int freespaces = 0;
        if(this.getDirection().equals("up")){
                int loopRow = this.getStartRow();
                for (int i = 0; i < lengthOfShip; i++) {
                    if (game.getGameBoard().getPiece(loopRow, this.getStartColumn()).getSymbol() == 'E') {
                        freespaces++;
                    }
                    loopRow += -1;
                }
        }
        else if(this.getDirection().equals("down")){
                int loopRow = this.getStartRow();
                for (int i = 0; i < lengthOfShip; i++) {
                    if (game.getGameBoard().getPiece(loopRow, this.getStartColumn()).getSymbol() == 'E') {
                        freespaces++;
                    }
                    loopRow += 1;
                }
        }
        else if(this.getDirection().equals("left")){
                int loopColumn = this.getStartColumn();
                for (int i = 0; i < lengthOfShip; i++) {
                    if (game.getGameBoard().getPiece(this.getStartRow(), loopColumn).getSymbol() == 'E') {
                        freespaces++;
                    }
                    loopColumn += -1;
                }
        }
        else if(this.getDirection().equals("right")){
                int loopColumn = this.getStartColumn();
                for (int i = 0; i < lengthOfShip; i++) {
                    if (game.getGameBoard().getPiece(this.getStartRow(), loopColumn).getSymbol() == 'E') {
                        freespaces++;
                    }
                    loopColumn += 1;
                }
        }
        return freespaces;

    }
    public String getFlagColor(BattleShipGame game){
        if (game.getTurnSymbol() == 'X'){
            return "blue";
        }
        else return "red";
    }

}
