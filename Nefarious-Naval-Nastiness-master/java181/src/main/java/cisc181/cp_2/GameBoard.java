package cisc181.cp_2;

public class GameBoard {
    // Board is a 2D array of TicTacToe Pieces
    private GamePiece[][] board;

    public GameBoard( GamePiece[][] board) {
        this.board = board;
    }

    public GamePiece[][] getBoard(){
        return this.board;
    }

    public GamePiece getPiece(int row, int column){
        return board[row][column];
    }

    public void setPiece(int row, int column, GamePiece piece) {
        board[row][column] = piece;
    }

    public boolean hasEmptySpace() {
        for (GamePiece[] row : board) {
            for (GamePiece value : row) {
                if (value.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInBounds(int row, int column) {
        return row >= 0 && column >= 0 &&
                row < getBoard().length &&
                column < getBoard()[row].length;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print("" + board[i][j].getSymbol() + '\t');
            }
            System.out.println();
        }
    }
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                buffer.append(board[i][j] + " ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }



    //accepts a start row, a start column, direction to change row, direction to change column, symbol
    public int numConsec( int row, int column, int dr, int dc,char symbol){
        int maxSequence = 0;
        int currentSequence = 0;

        //System.out.println("in num conseg row: " + row + " col: " + column + "rowc" + dr + "col" + dc);
        while (isInBounds(row, column)) {
            // we are looping down the sequence looking for chars == symbol
            if (board[row][column].getSymbol() == symbol) {
                currentSequence++;
            }
            else {
                // the square does not belong to player
                currentSequence = 0;
            }
            maxSequence = Math.max(maxSequence, currentSequence);

            row += dr;
            column += dc;
        }

        return maxSequence;
    }


    // find max number of symbols in a row
    public int getMaxConsec(char symbol) {
        int maxScore = 0;
        for (int i = 0; i < this.getBoard().length; i++) {
            // for each row (left to right)
            // start at row 0, column 0 , don't change row, change column +1
            maxScore = Math.max(maxScore, numConsec(i, 0, 0, 1, symbol));
            // for each column (top to bottom)
            // start at row 0, column i , change row + 1, don't change column
            maxScore = Math.max(maxScore, numConsec(0, i, 1, 0, symbol));
        }

        // for diagonal (top-left to bottom-right)
        // start at row 0, column i , change row + 1, change column + 1
        maxScore = Math.max(maxScore, numConsec(0, 0, 1, 1, symbol));
        // for diagonal (top-right to bottom-left)
        // start at row 0, column 2 , change row + 1, change column -1
        int colind = getBoard()[0].length - 1;
        maxScore = Math.max(maxScore, numConsec(0, colind, 1, -1, symbol));
        return maxScore;
    }
}
