import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToeGame {

// FINISH THE VARIABLE DECLARATION
   /**
	* The board of the game, stored as a one dimension array.
	*/
	 private CellValue[] board;


   /**
	* level records the number of rounds that have been
	* played so far. 
	*/
	private int level;

   /**
	* gameState records the current state of the game.
	*/
	private GameState gameState;


   /**
	* lines is the number of lines in the grid
	*/
   private int lines;

   /**
	* columns is the number of columns in the grid
	*/
   private int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game
	*/
   private int sizeWin;


   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	public TicTacToeGame(){
		this(3,3,3);
		Arrays.fill(board, CellValue.EMPTY);
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){
		this(lines,columns,3);
		Arrays.fill(board, CellValue.EMPTY);


	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as 
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){
		this.board=new CellValue[lines*columns];
		Arrays.fill(board, CellValue.EMPTY);
		this.lines=lines;
		this.columns=columns;
		this.sizeWin=sizeWin;
		gameState=GameState.PLAYING;


	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){

		return this.lines;

	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){
		return this.columns;
	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){
		return this.level;
	}

  	/**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){
		return this.sizeWin;


	}

   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){
        return this.gameState;

	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should 
	* play next.
	*
	* @return 
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
	public CellValue nextCellValue(){
		// YOUR CODE HERE
		if (this.level%2==0){
			return CellValue.X;

		}
		else{
			return CellValue.O;
		}



	}

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return 
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {

		// YOUR CODE HERE
		if (i<=0 || i> board.length){
			System.out.println("invalid index");
			return null;
		}
		else{
			return board[i-1];
		}

		}


   /**
	* This method is called when the next move has been
	* decided by the next player. It receives the index
	* of the cell to play as parameter.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, is is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded. 
	* the  winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been 
    * selected by the next player
  	*/
	public void play(int i) {


		// YOUR CODE HERE
		if( valueAt(i)==null){
			System.out.println("Invalid");
		}
		else if(valueAt(i)!=CellValue.EMPTY){
			System.out.println("not empty");

		}
		else {
			board[i-1]=nextCellValue();
			// a finir
		}
		setGameState(i);
		this.level++;

	
	}


   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set in
	* the method play(int i)
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (i.e. the game was playing). Therefore, it only needs to 
	* check if playing at index i has concluded the game, and if
	* set the oucome correctly
	* 
   	* @param i
    *  the index of the cell in the array board that has just 
    * been set
  	*/


	private void setGameState(int i){

		// YOUR CODE HERE // cheks if someone won vertically diagonally horizontal

		boolean won=false;
		int compteur = 0;// diagonal gauche droite
		for(int j =0; j<getColumns(); j++  ){
			if(valueAt((i-1)%columns+j*columns+1)==valueAt(i)){
				compteur++;

			}
			else{
				compteur=0;
			}

		}
		won = compteur>=sizeWin;
		if (!won) { // diagonale droite gauche
			for (int j = 0; j < getColumns(); j++) {
				if (valueAt((i-1)%columns+1+j*(columns-1)) == valueAt(i)) {
					compteur++;

				} else {
					compteur = 0;
				}
			}
			won = compteur>=sizeWin;
		}
		if (!won) { // verticale
			for (int j = 0; j < getColumns(); j++) {
				if (valueAt((i-1)%columns+columns*j+1) == valueAt(i)) {
					compteur++;

				} else {
					compteur = 0;
				}
			}
			won = compteur>=sizeWin;
		}
		if (!won) { //horizontal
			for (int j = 0; j < getColumns(); j++) {
				if (valueAt(((i-1)/columns)*columns+j+1) == valueAt(i)) {
					compteur++;


				} else {
					compteur = 0;
				}
			}
			won = compteur>=sizeWin;
		}



			if(won){
			if(valueAt(i)== CellValue.X){
				gameState=GameState.XWIN;
			}
		    else {
			  	gameState=GameState.OWIN;
			}
		}
		boolean emptyCell=false;
		for(int k =0;k<columns*lines;k++){
			if (board[k]==CellValue.EMPTY){
				emptyCell=true;
				break;
			}
		}
			if (!won && !emptyCell){
				gameState=GameState.DRAW;
			}
	}



   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	* 
   	* @return
    *  String representation of the game
  	*/

	public String toString(){

		// YOUR CODE HERE
		String string="";
		for(int i=0;i<lines;i++ ) {
			for (int j = 0; j < columns; j++) {
				string+=" " + board[j+i*j] + " ";
				if (j != columns - 1) {
					string+="|";
				}

			}
			string+="\n";
		}

		return string;
	}

}