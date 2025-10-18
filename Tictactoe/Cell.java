package com.example.SpringTest.tictactoe;

public class Cell {
    Symbol symbol;

    public Cell(Symbol symbol) {
        this.symbol = Symbol.EMPTY;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }



}
