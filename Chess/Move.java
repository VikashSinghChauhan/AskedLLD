package com.example.SpringTest.chessgame;

public interface Move {
    boolean canMove(ChessBoard board, int sr, int sc, int er, int ec);
}

class StraightMove implements Move{
    @Override
    public boolean canMove(ChessBoard board, int sr, int sc, int er, int ec) {
        if(sr != er && sc!=ec)return false;
        int dr = Integer.compare(er, sr);
        int dc = Integer.compare(ec, sc);
        sr += dr;
        sc += dc;
        while(sr != er || sc != ec){
            if(board.get(sr, sc)!=null)return false;
            sr += dr;
            sc += dc;
        }
        return true;
    }
}

class DiagonalMove implements Move{
    @Override
    public boolean canMove(ChessBoard board, int sr, int sc, int er, int ec) {
        if(Math.abs(er - sr) != Math.abs(ec-sc))return false;
        int dr = Integer.compare(er, sr);
        int dc = Integer.compare(ec, sc);
        sr += dr;
        sc += dc;

        while(sr != er){
            if(board.get(sr,sc)!=null)return false;
            sr += dr;
            sc += dc;
        }
        return true;
    }
}

class KingMove implements Move{
    @Override
    public boolean canMove(ChessBoard board, int sr, int sc, int er, int ec) {
        return Math.abs(er-sr)<=1 && Math.abs(ec-sc)<=1;
    }


}

class KnightMove implements Move{
    @Override
    public boolean canMove(ChessBoard board, int sr, int sc, int er, int ec) {
        int dr = Math.abs(er-sr);
        int dc = Math.abs(ec -sc);
        return (dr==2 && dc==1 ) || (dr==1 && dc==2);
    }
}

class PawnMove implements Move{
    @Override
    public boolean canMove(ChessBoard board, int sr, int sc, int er, int ec) {
        //get piece because their move is unidirectional

        Piece p = board.get(sr, sc);
        int dir = p.color == 'W'?1:-1;

        if((sc == ec) && board.get(er,ec)==null)
        {
            return ((er-sr) == dir);
        }
        System.out.println("hello 4");
        if(Math.abs(ec-sc)==1 && er-sr==dir && board.get(er,ec)!=null)return true;
        return false;
    }
}
