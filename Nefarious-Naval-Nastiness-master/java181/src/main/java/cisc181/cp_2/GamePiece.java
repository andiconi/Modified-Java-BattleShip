package cisc181.cp_2;

public class GamePiece  {

    public static final char EMPTY = 'E';
    private char symbol;

    public GamePiece(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isEmpty() {
        return symbol == EMPTY;
    }

    public String toString() {
        return Character.toString(symbol);
    }

    @Override
    public boolean equals(Object other) {

        // If the object is compared with itself then return true
        if (other == this) {
            return true;
        }
        // Check if other is an instance of GamePiece or not
        if (!(other instanceof GamePiece)) {
            return false;
        }
        // typecast other to GamePiece so that we can compare data members
        GamePiece otherPiece = (GamePiece)other;

        // Compare the data members and return accordingly
        return this.symbol == otherPiece.getSymbol();
    }
}
