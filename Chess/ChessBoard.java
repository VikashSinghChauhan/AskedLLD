package com.example.SpringTest.chessgame;

public class ChessBoard {
    Piece[][] board;
    int gameState =0; //0-progress, 1-white wins, 2-black wins
    int nextTurn =0; //0-white, 1 black

    public ChessBoard(String[][] init) {
        int n = init.length;
        int m = init[0].length;

        board = new Piece[n][m];

        for(int r=0;r<n;r++)
        {
            for(int c=0;c<m;c++)
            {
                if(init[r][c].length() >=2)
                {
                    char color = init[r][c].charAt(0);
                    char type = init[r][c].charAt(1);
                    board[r][c] = PieceFactory.create(color, type);
                }
                else board[r][c]=null;
            }
        }
    }


    String move(int sr, int sc, int er, int ec){
        if(gameState !=0)return "invalid";
        Piece p = get(sr, sc), target = get(er, ec);
        if(p == null || !valid(er, ec))return "invalid";
        if(target != null && target.color == p.color)return "invalid";



        if(!p.canMove(this, sr, sc, er, ec))return "invalid";

        board[sr][sc] = null;
        board[er][ec] = p;
        nextTurn ^=1;

        if(target!=null && target.type == 'K')
                gameState = target.color =='B'?1:2;

        return target == null ? "":""+target.color + target.type;
    }

    Piece get(int r, int c){
        return valid(r,c)?board[r][c]:null;
    }

    int getNextTurn(){
        return gameState==0 ? nextTurn:-1;
    }

    int getGameState(){
        return gameState;
    }
    private boolean valid(int r, int c){
        return r>=0 && r<board.length && c>=0 && c<board[0].length;
    }
}
