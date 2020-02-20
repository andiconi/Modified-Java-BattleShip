package edu.udel.abrevett.nnnastiness;

public class ButtonData {
    private int row, col;
    private String data;

    protected ButtonData(int row, int col, String data){
        this.row = row;
        this.col = col;
        this.data = data;
    }

    //Accessors
    public int getRow(){
        return this.row;
    }
    public int getCol() {
        return col;
    }
    public String getData() {
        return data;
    }
}
