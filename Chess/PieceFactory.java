package com.example.SpringTest.chessgame;

public class PieceFactory {
    /*
    Bishop -- camel(utt)
    Knight -- horce(ghoda)
    Queen -- wazir
    Rook -- Hathi
    Pawn -- piddi
     */
    static Piece create(char color, char type){
        switch (type){
            case 'Q': return new Piece(color, 'Q', new Move[]{new StraightMove(), new DiagonalMove()});
            case 'R': return new Piece(color, 'R', new Move[]{new StraightMove()});
            case 'B': return new Piece(color, 'B', new Move[]{new DiagonalMove()});
            case 'K': return new Piece(color, 'K', new Move[]{new KingMove()});
            case 'H': return new Piece(color, 'H', new Move[]{new KnightMove()});
            case 'P': return new Piece(color, 'P', new Move[]{new PawnMove()});
            default:return null;
        }
    }
}
