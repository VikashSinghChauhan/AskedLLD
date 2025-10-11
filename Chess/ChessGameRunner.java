package com.example.SpringTest.chessgame;

public class ChessGameRunner {
    public static void main(String[] args) {
        String[][] board = {
                {"WR","WH","WB","WQ","WK","WB","WH","WR"},
                {"WP","WP","WP","WP","WP","WP","WP","WP"},
                {"","","","","","","",""},
                {"","","","","","","",""},
                {"","","","","","","",""},
                {"","","","","","","",""},
                {"BP","BP","BP","BP","BP","BP","BP","BP"},
                {"BR","BH","BB","BQ","BK","BB","BH","BR"}
        };

        ChessBoard chessBoard = new ChessBoard(board);
        System.out.println("Initial Next turn : " + chessBoard.getNextTurn());
        System.out.println("Game status : "+ chessBoard.getGameState());

        System.out.println(chessBoard.move(1,5,2,5));
        System.out.println("Next turn : " + chessBoard.getNextTurn());
        System.out.println("Game status : "+ chessBoard.getGameState());

        System.out.println(chessBoard.move(6, 6, 5, 6));
        System.out.println("Next turn : " + chessBoard.getNextTurn());
        System.out.println("Game status : "+ chessBoard.getGameState());


    }

}
