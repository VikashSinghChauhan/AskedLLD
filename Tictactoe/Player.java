package com.example.SpringTest.tictactoe;

public class Player {
    String name;
    Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void notifyPlayer(Player winnerPlayer ){
        System.out.println("Player won :: " + winnerPlayer.getName());
    }
}
