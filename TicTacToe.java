//Name: Mathias Brook
import java.util.Scanner;

public class TicTacToe{
  
   //set class variables here
   private char[][] board = new char[3][3];
   private char playerComputer;
   private Scanner scan = new Scanner(System.in); //one Scanner for all input
   //You may add two additional class variables here
  
  
  //This is a sample board so the player knows how the numbers correspond to the spots in the board
  //Constants for PLAYER and COMPUTER if you want to use them
   private static final String sampleboard =" 1 | 2 | 3\n-----------\n 4 | 5 | 6\n-----------\n 7 | 8 | 9";
   private static final char PLAYER = 'X';
   private static final char COMPUTER = 'O';
  
  //Default constructor.  
   public TicTacToe(){
   
   }
   
   
  //Takes an integer, as indicated by the sample board and
  //sets the correct index of the matrix to the value of playerComputer
  //which toggles between X and O
  //keeps same player if the play is not legal
  //otherwise, switches player
   private void set(int index){
      int row = 0;
      index--;
      if (index < 3) row = 0; //converts index into raw index
      else if (index > 2 && index < 6) {
         row = 1;
         index -= 3;
      }
      else if (index > 5) {
         row = 2;
         index -= 6;
      }
      if (index >= 0 && index <= 2 && board[row][index] == ' ') { //sees if current index is valid and is empty then fill
         board[row][index] = playerComputer;
         if (playerComputer == 'x') //places x's or o's based on the player and sets the next turn to the other player
            playerComputer = 'o';
         else if (playerComputer == 'o')
            playerComputer = 'x';
      } else if (index >= 0 && index <= 2 && board[row][index] != ' ' && playerComputer == 'x') System.out.println("Your current square already has a character, please select another one!"); //does nothing if players index has already been filled
   }
  
 
  //returns X if X has won
  //returns O if O has won
   //returns D if the game has ended in a draw
   //returns N if no one has won and there are still empty spaces
   private char gameOver(){
      for (int i = 0; i < board.length; i++) { //checks all rows and columns for a full row of the same characters and returns the character
         if (board[i][0] == board[i][1] && board[i][1] == board[i][2])
            if (board[i][0] != ' ') return Character.toUpperCase(board[i][0]);
         if (board[0][i] == board[1][i] && board[1][i] == board[2][i])
            if (board[0][i] != ' ') return Character.toUpperCase(board[0][i]);
      }
      if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) //checks for diagonals for the same character in all three spaces
         if (board[0][0] != ' ') return Character.toUpperCase(board[0][0]);
      if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
         if (board[0][2] != ' ') return Character.toUpperCase(board[0][2]);
      for (int r = 0; r < board.length; r++) { //detects if there is an empty space still if no winning conditions are met returns none
         for (int c = 0; c < board.length; c++) {
            if (board[r][c] == ' ') return 'N';
         }
      }
      return 'D'; //if there are no winning conditions met and there are no empty space it returns a tie
   }
       
   //initialize the array with space characters (32)
   //sets playerComputer to X 
   private void initialize(){ //set all board spaces to the character space
     for (int r = 0; r < board.length; r++)
         for (int c = 0; c < board.length; c++)
            board[r][c] = ' ';
     playerComputer = 'x';
   }    
    
   private int prevent(){ //used in hard mode to prevent the player from winning, diagonals have higher importance
      int row = -1;
      int col = -1;
      //detects possible wins in diagonals
      int count = 0;
      if (board[0][0] == 'x') count++;
      if (board[1][1] == 'x') count++;
      if (board[2][2] == 'x') count++;
      if (count == 2) { //if there 2 x's in diagonal position get the raw index of the empty space
         if (board[0][0] == ' ') {
            row = 0;
            col = 0;
         }
         else if (board[1][1] == ' ') {
            row = 1;
            col = 1;
         }
         else if (board[2][2] == ' ') {
            row = 2;
            col = 2;        
         }
      }
      count = 0;
      if (board[0][2] == 'x') count++;
      if (board[1][1] == 'x') count++;
      if (board[2][0] == 'x') count++;
      if (count == 2) {
         if (board[0][2] == ' ') {
            row = 0;
            col = 2;
         }
         else if (board[1][1] == ' ') {
            row = 1;
            col = 1;
         }
         else if (board[2][0] == ' ') {
            row = 2;
            col = 0;
            
         }
      }
      for (int r = 0; row == -1 && col == -1 && r < board.length; r++) { //detects possible wins in rows and columns
         int countR = 0;
         int countC = 0;
         for (int c = 0; c < board.length; c++) { //counts the number of x's in the rows and columns 
            if (board[r][c] == 'x') countC++;
            if (board[c][r] == 'x') countR++;
         }
         if (countR == 2) { //if there are 2 x's in a certain column get the raw index of the empty space in the columun
            for (int c = 0; c < board.length; c++)
               if (board[c][r] == ' ') {
                  row = c;
                  col = r;
               }
         }
         if (countC == 2) { //if there are 2 x's in a certain row get the raw index of the empty space in the row
            for (int c = 0; c < board.length; c++)
               if (board[r][c] == ' ') {
                  row = r;
                  col = c;
               }
         }
         if (row != -1 && col != -1) break;
      }
      if (col != -1 && row != -1) { //converts the raw index to normal index for set method
         col++;
         if (row == 1) col+=3;
         else if (row == 2) col+=6;
         set(col);
         return col;
      }
      return 0;
   }

  
  //prints the board following the same model as the sample board, showing
  //the plays and blanks where no play has happened yet
   public String toString(){
     return " " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + "\n-----------\n "+ board[1][0] + " | " + board[1][1] + " | " + board[1][2] + "\n-----------\n " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]; 
   }
 
  //Plays the game. The computer plays randomly
  //Reset the board and any class variables before starting 
  //each game.
   public void selectGame() {
      System.out.println("Select your difficulty. Type 'easy' for easy mode and 'hard' for hard mode");
      String difficulty = scan.next(); //String scan for easy and hard
      while (true) {
         if (difficulty.toLowerCase().equals("easy")) {
            playeasy();
            break;
         }
         else if (difficulty.toLowerCase().equals("hard")) { //easy and hard are valid
            playhard();
            break;
         }
         difficulty = scan.next(); //waits for another string input if previous string was not "easy" or "hard"
      }
   }
   public void playeasy(){
    initialize(); //sets up the board
    System.out.println(this + "\nYour turn. Enter a number between 1-9");
    boolean nextPlayerTurn = true;
    char over = 'N';
    int input = scan.nextInt(); //scans for player number input
    while (true) {      
      if (input >= 1 && input <= 9 && playerComputer == 'x') { //if player's input is between 1 - 9 and the square is not occupied, place an X there
         set(input);
         if (playerComputer == 'o') System.out.println("Player " + input + "\n" + this);
         over = gameOver(); //tests for game over
      } else if ((input < 1 || input > 9) && playerComputer == 'x') { //if player's input is not between 1 - 9
         System.out.println("Your input is invalid. Please type a number from 1 - 9");
      }
      while (playerComputer == 'o' && over == 'N') { //makes a computer play loop to continue playing until a space has been sucessfully covered
         int random = (int)((Math.random()*9)+1); //generates a random number between 1 - 9
         set(random); //puts an o in a random space
         if (playerComputer == 'x') System.out.println("Computer " + random + "\n" + this);
         over = gameOver(); //tests for game over
      }
      if (over == 'X') { //if game is over, display winner and break the while input loop
         System.out.println("The Player (X) has won!" + "\n" + this);
         break;
      } else if (over == 'O') {
         System.out.println("The Computer (O) has won!" + "\n" + this);
         break;
      } else if (over == 'D') {
         System.out.println("Draw!" + "\n" + this);
         break;
      }
      if (playerComputer == 'x') System.out.println("Your turn. Enter a number between 1-9");
      input = scan.nextInt();
    }

   }
   public void playhard(){
    initialize(); //sets up the board
    System.out.println(this + "\nYour turn. Enter a number between 1-9");
    boolean nextPlayerTurn = true;
    char over = 'N';
    int input = scan.nextInt(); //scans for player number input
    while (true) {      
      if (input >= 1 && input <= 9 && playerComputer == 'x') { //if player's input is between 1 - 9 and the square is not occupied, place an X there
         set(input);
         if (playerComputer == 'o') System.out.println("Player " + input + "\n" + this);
         over = gameOver(); //tests for game over
      } else if ((input < 1 || input > 9) && playerComputer == 'x') { //if player's input is not between 1 - 9
         System.out.println("Your input is invalid. Please type a number from 1 - 9");
      }
      while (playerComputer == 'o' && over == 'N') { //makes a computer play loop to continue playing until a space has been sucessfully covered
         int random = (int)((Math.random()*9)+1); //generates a random number between 1 - 9
         int block = prevent(); //scans for any possible player wins to prevent, returns the square it blocked or 0
         if (block != 0) random = block; //so the right square gets printed
         if (playerComputer == 'o') set(random); //puts an o in a random space if no possible wins for player
         if (playerComputer == 'x') System.out.println("Computer " + random + "\n" + this);
         over = gameOver(); //tests for game over
      }
      if (over == 'X') { //if game is over, display winner and break the while input loop
         System.out.println("The Player (X) has won!" + "\n" + this);
         break;
      } else if (over == 'O') {
         System.out.println("The Computer (O) has won!" + "\n" + this);
         break;
      } else if (over == 'D') {
         System.out.println("Draw!" + "\n" + this);
         break;
      }
      if (playerComputer == 'x') System.out.println("Your turn. Enter a number between 1-9");
      input = scan.nextInt();
    }

   } 
  
        
   public static void main(String[] args){
    //The sample game should result in the computer winning on the
    //bottom row
      TicTacToe game = new TicTacToe();
      System.out.println(sampleboard);
      System.out.println("Game Simulation 1");
      game.initialize();
      game.set(5);
      System.out.println(game);
      System.out.println(" ");
      game.set(3);
      System.out.println(game);
      System.out.println(" ");
      game.set(6);
      System.out.println(game);
      System.out.println(" ");
      game.set(4);
      System.out.println(game);
      System.out.println(" ");
      game.set(2);
      System.out.println(game);
      System.out.println(" ");
      game.set(8);
      System.out.println(game);
      System.out.println(" ");
      game.set(9);
      System.out.println(game);
      System.out.println(" ");
      game.set(1);
      System.out.println(game);
      System.out.println(" ");
      game.set(7);
      System.out.println(game);
      System.out.println("Expected Output D " +game.gameOver()); 
      
      
      game.initialize();
      System.out.println("Game Simulation 2");
      game.set(1);
      System.out.println(game); 
      System.out.println(game.gameOver()); 
      game.set(7);
      System.out.println(game); 
      System.out.println(game.gameOver());
      game.set(7);
      System.out.println("Expected Output X " + game.playerComputer); 
      game.set(5);
      System.out.println(game); 
      System.out.println(game.gameOver()); 
      game.set(9);
      System.out.println(game); 
      System.out.println(game.gameOver());  
      game.set(6);
      System.out.println(game); 
      System.out.println(game.gameOver());
      game.set(8);
      System.out.println(game); 
      System.out.println("Expected O " + game.gameOver());
      
      game.selectGame();
      //game.playhard(); 
      //game.playeasy();
    
   
   }
                                       
}