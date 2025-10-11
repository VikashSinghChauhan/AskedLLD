package com.example.SpringTest.chessgame;

public class ChessGame {
    private ChessBoard board;

    public ChessGame(String[][] chessBoard) {
        this.board = new ChessBoard(chessBoard);
    }

    public String move(int sr, int sc, int er, int ec)
    {
        return board.move(sr, sc, er, ec);
    }

    public int getGameStatus()
    {
        return board.getGameState();
    }
    public int getNextTurn(){
        return board.getNextTurn();
    }

}
