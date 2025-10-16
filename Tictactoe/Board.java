package com.example.SpringTest.tictactoe;

import java.util.List;

public class Board {
    int size;
    Cell[][] board;
    int movesCount;


    public Board(int size) {
        this.size = size;
        this.board = new Cell[size][size];
        for(int i=0;i<size; i++)
        {
            for(int j=0;j<size;j++)
            {
                board[i][j] = new Cell(Symbol.EMPTY);
            }
        }
        movesCount=0;
    }

    public boolean placeSymbol(int row, int col, Player player)
    {
        List<Move> moves = List.of(new VerticalMove(), new HorizontalMove(), new DiagonalMove());
        if(row<0 || row>=size || col<0 || col>=size)
        {
            System.out.println("Invalid move");
            return false;
        }
        if(board[row][col].getSymbol()!=Symbol.EMPTY){
            System.out.println("Invalid position: cell is already occupied");
            return false;
        }
        board[row][col].setSymbol(player.symbol);
        movesCount++;
        return true;
    }

    public boolean checkDraw()
    {
        return size*size == movesCount;
    }

}
