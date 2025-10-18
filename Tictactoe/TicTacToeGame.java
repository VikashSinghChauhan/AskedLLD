package com.example.SpringTest.tictactoe;

public class TicTacToeGame {
    public static void main(String[] args) {
        Player o = new Player("o", Symbol.O);
        Player x = new Player("x", Symbol.X);

        Game game = new Game(o,x);
        game.makeMove(o,0,0);
        game.makeMove(x,1,0);
        game.makeMove(o,0,1);
        game.makeMove(x,1,1);
        game.makeMove(o,2,2);
        game.makeMove(x,1,2);


    }
}


//https://www.youtube.com/watch?v=gktZsX9Z8Kw 
//watch this video first, sol based on this.
