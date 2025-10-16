package com.example.SpringTest.tictactoe;

import java.util.List;

public class Game {
    Board board;
    Player player1;
    Player player2;
    Player winner;
    GameStatus gameStatus;
    List<Move> moveList;

    public Game(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moveList = List.of(new VerticalMove(), new HorizontalMove(), new DiagonalMove());
    }

    public void makeMove(Player player, int row, int col)
    {
        if(gameStatus==GameStatus.IN_PROGRESS)
        {
            board.placeSymbol(row, col, player);
            if(checkWinner(row,col,moveList, player))
            {
                gameStatus = player.getSymbol().equals('O')?GameStatus.WINNER_O:GameStatus.WINNER_X;
            }
            else if(board.checkDraw())
            {
                gameStatus=GameStatus.DRAW;
            }
        }
    }

    public boolean checkWinner(int row, int col, List<Move> moves, Player player)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(board.board[i][j].getSymbol());
            }
            System.out.println("");
        }
        for(Move move : moves)
        {
            if(move.move(row, col, player, board))
            {
                System.out.println("Player won" + player.getSymbol());
                return true;
            }
        }
        return false;

    }
}
