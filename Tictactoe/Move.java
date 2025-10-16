package com.example.SpringTest.tictactoe;

public interface Move {
    boolean move(int row, int col, Player player, Board board);
}

class VerticalMove implements Move{
    @Override
    public boolean move(int row, int col, Player player, Board board) {
        boolean won = true;
        for(int r=0;r<board.size;r++)
        {
            if(!board.board[r][col].equals(player.symbol))
            {
                won = false;
                break;
            }
        }
        return won;
    }
}

class HorizontalMove implements Move{
    @Override
    public boolean move(int row, int col, Player player, Board board) {
        boolean won = true;
        for(int c=0;c<board.size;c++)
        {

            if(!board.board[row][c].getSymbol().equals(player.symbol))
            {
                won = false;
                break;
            }
        }
        return won;
    }
}

class DiagonalMove implements Move{
    @Override
    public boolean move(int row, int col, Player player, Board board) {
        boolean won = true;
        for(int c=0;c<board.size;c++)
        {
            if(!board.board[c][c].equals(player.symbol))
            {
                won = false;
                break;
            }
        }
        if(won)return won;
        for(int c=0;c<board.size;c++)
        {
            if(!board.board[c][board.size-c-1].equals(player.symbol))
            {
                won = false;
                break;
            }
        }

        return won;
    }
}


