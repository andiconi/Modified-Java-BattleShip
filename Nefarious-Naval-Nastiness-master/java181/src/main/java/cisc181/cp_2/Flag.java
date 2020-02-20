package cisc181.cp_2;

public class Flag extends GamePiece {
    private String color;
    public Flag(String Color){
        super('#');
        this.color = color;

    }

    @Override
    public char getSymbol() {
        return super.getSymbol();
    }

    public String getColor() {
        return color;
    }
}
