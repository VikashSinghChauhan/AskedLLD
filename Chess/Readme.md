| Pattern                                | Where It Appears                                                                                   | Why It’s Used                                                                                                                                                                                               |
| -------------------------------------- | -------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Factory Method (or Simple Factory)** | `ChessPieceFactory`                                                                                | Centralizes creation of different `Piece` subclasses (`KingPiece`, `KnightPiece`, `PawnPiece`), removing the need for `new KingPiece(...)` scattered everywhere. Makes it easier to extend with new pieces. |
| **Strategy Pattern**                   | `Move` interface + concrete strategies (`StraightMove`, `DiagonalMove`, `PawnPiece`’s logic, etc.) | Each `Piece` delegates *how it moves* to a `Move` strategy (e.g. diagonal, straight). So you can add or combine new movement strategies without touching core game logic.                                   |
| **Singleton Pattern**                  | `ChessPieceFactory.getInstance()`                                                                  | Ensures there’s only one factory creating pieces, keeping consistent movement logic and reducing memory footprint.                                                                                          |
| **Composition / Delegation**           | `Piece` holds an array of `Move` strategies                                                        | Instead of using inheritance for each movement type, the class *composes* behavior dynamically. A queen, for example, has `[straightMove, diagonalMove]`.                                                   |
| **State Pattern (lightweight usage)**  | `ChessBoard` uses `gameState` (0=in progress, 1=white wins, 2=black wins)                          | Represents the current *state* of the game that influences allowed transitions (no more moves after win).                                                                                                   |


why these patterns make sense : 

| Concern                     | Why this pattern helps                                                                                                                                          |
| --------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Adding new piece types**  | Factory pattern isolates piece creation → just add one new case (e.g., `case 'N': return new NinjaPiece(...)`).                                                 |
| **Changing movement rules** | Strategy pattern allows flexible mix/match of moves — e.g. “SuperQueen” could just have `{straightMove, diagonalMove, knightMove}` without changing base logic. |
| **Central consistency**     | Singleton ensures consistent movement behavior and piece factory logic across board.                                                                            |
| **Board logic simplicity**  | `ChessBoard` just checks rules and delegates move validity — doesn’t need to know how rooks or bishops move.                                                    |


Flow :: 

Solution.move()
↓
ChessBoard.move()
↓
Piece.canMove()
↓
Move.canMove()   ← (Strategy pattern in action)
↓
return true/false
↓
ChessBoard updates game state

Benefits :: 

✅ Extensible — add new piece types easily
✅ Maintainable — movement logic modular
✅ Testable — each Move strategy can be unit-tested
✅ Clear separation — ChessBoard handles game rules, not piece details