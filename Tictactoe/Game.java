package com.example.SpringTest.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    Board board;
    Player player1;
    Player player2;
    Player winner;
    GameStatus gameStatus;
    List<Move> moveList;
    List<Player> observerList = new ArrayList<>();

    public Game(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moveList = List.of(new VerticalMove(), new HorizontalMove(), new DiagonalMove());
        observerList.addAll(Arrays.asList(player1, player2));
    }

    public void makeMove(Player player, int row, int col)
    {
        if(gameStatus==GameStatus.IN_PROGRESS)
        {
            board.placeSymbol(row, col, player);
            if(board.checkWinner(row,col,moveList, player))
            {
                Player winnerPlayer;
                gameStatus = player.getSymbol().equals('O')?GameStatus.WINNER_O:GameStatus.WINNER_X;
                winnerPlayer = (gameStatus == GameStatus.WINNER_O)?player1:player2;

                for(Player plyr : observerList)
                {
                    plyr.notifyPlayer(winnerPlayer);
                }
            }
            else if(board.checkDraw())
            {
                gameStatus=GameStatus.DRAW;
            }
        }
    }


}
