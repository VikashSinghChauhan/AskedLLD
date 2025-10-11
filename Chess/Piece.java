package com.example.SpringTest.chessgame;

public class Piece {
    char color, type;
    Move[] moves;

    public Piece(char color, char type, Move[] moves) {
        this.color = color;
        this.type = type;
        this.moves = moves;
    }

    boolean canMove(ChessBoard board, int sr, int sc, int er, int ec){
        for(Move m : moves)
        {
            if(m.canMove(board, sr, sc, er, ec))return true;

        }
        return false;
    }
}
