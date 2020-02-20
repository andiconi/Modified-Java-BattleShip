package cisc181.cp_2;

public class Ship extends GamePiece {
    private int length;
    private String type;
    public Ship(int length, char symbol, String type){
        super(symbol);
        this.length = length;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    @Override
    public char getSymbol() {
        return super.getSymbol();
    }

    public int getLength() {
        return length;
    }
}
