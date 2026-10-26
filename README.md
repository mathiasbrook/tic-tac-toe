# Tic-Tac-Toe

Tic-Tac-Toe in the Java console against a computer, with an easy mode and a hard mode.

## Features
- Easy mode: the computer plays random open squares
- Hard mode: the computer checks rows, columns and both diagonals and blocks you when you have two in a row (otherwise it plays randomly)
- Squares are picked with the numbers 1-9 shown on a sample board at the start
- Rejects numbers outside 1-9 and squares that are already taken
- Detects wins on rows, columns and diagonals, and draws

## How to run
```
javac TicTacToe.java && java TicTacToe
```
Type `easy` or `hard`, then enter square numbers. You are X and always go first.

To run the test games from class instead:
```
java TicTacToe test
```

## Sample output
A real hard mode game (lines starting with `>` are what I typed). You can see the computer block me at 9, 4 and 8:

```
 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9
Select your difficulty. Type 'easy' for easy mode and 'hard' for hard mode
> hard
   |   |  
-----------
   |   |  
-----------
   |   |  
Your turn. Enter a number between 1-9
> 5
Player 5
   |   |  
-----------
   | x |  
-----------
   |   |  
Computer 3
   |   | o
-----------
   | x |  
-----------
   |   |  
Your turn. Enter a number between 1-9
> 1
Player 1
 x |   | o
-----------
   | x |  
-----------
   |   |  
Computer 9
 x |   | o
-----------
   | x |  
-----------
   |   | o
Your turn. Enter a number between 1-9
> 9
Your current square already has a character, please select another one!
Your turn. Enter a number between 1-9
> 3
Your current square already has a character, please select another one!
Your turn. Enter a number between 1-9
> 7
Player 7
 x |   | o
-----------
   | x |  
-----------
 x |   | o
Computer 4
 x |   | o
-----------
 o | x |  
-----------
 x |   | o
Your turn. Enter a number between 1-9
> 2
Player 2
 x | x | o
-----------
 o | x |  
-----------
 x |   | o
Computer 8
 x | x | o
-----------
 o | x |  
-----------
 x | o | o
Your turn. Enter a number between 1-9
> 4
Your current square already has a character, please select another one!
Your turn. Enter a number between 1-9
> 6
Player 6
 x | x | o
-----------
 o | x | x
-----------
 x | o | o
Draw!
 x | x | o
-----------
 o | x | x
-----------
 x | o | o
```

## Concepts used
- 2D arrays (`char[3][3]` board)
- Converting a square number 1-9 into a row and column
- Nested loops to check rows, columns and diagonals for a win or a block
- Private helper methods and a `toString()` for printing the board
- `Scanner` input and `Math.random()`

## What I'd add next
- Make hard mode also try to win, not just block (or use minimax so it never loses)
- Let the player pick X or O and who goes first
- Keep score over multiple games
- Handle non-number input without crashing
