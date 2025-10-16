package com.example.SpringTest.tictactoe;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY('_');

    private final char symbol;
    Symbol(char symbol) {
        this.symbol = symbol;
    }
}
