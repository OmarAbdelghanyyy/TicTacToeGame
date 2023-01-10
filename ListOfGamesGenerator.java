import java.util.LinkedList;

public class ListOfGamesGenerator {

	/**
	 * generates all different games for the specified
	 * parameters. Each game is recorded only once.
	 * once a game is finished, it is not extended further
	 * @param lines
	 *  the number of lines in the game
	 * @param columns
	 *  the number of columns in the game
	 * @param winLength
	 *  the number of cells that must be aligned to win.
	 * @return
	 * a list of lists of game instances, ordered by levels
	 */
	public static LinkedList<LinkedList<TicTacToeGame>> generateAllGames(int lines, int columns, int winLength)
	{
		//Initialises empty list of lists of TicTacToeGames
		LinkedList<LinkedList<TicTacToeGame>> listAllGames = new LinkedList<>();
		// Initialises a new TicTacToeGame that is empty
		TicTacToeGame game = new TicTacToeGame(lines, columns, winLength);
		listAllGames.add(new LinkedList<>()); // adds empty list to listAllGames
		listAllGames.get(0).add(game); // adds unplayed game at index 0 since at level 0 no move has been played

		boolean maxLevel = false;
		int levels = 0;
		//loop for each level until no more populated levels can be created
		while (!maxLevel)
		{
			// for each level a new linkedList is created
			LinkedList<TicTacToeGame> listLevel = new LinkedList<>();
			// loop that goes over each element of the previous level
			for (int i = 0; i < listAllGames.get(levels).size(); i++)
			{
				TicTacToeGame base = listAllGames.get(levels).get(i); // at each iteration one of the boards of the previous level is used as the base board
				//iterates every cell of the board
				for (int k = 0; k < base.lines * base.columns && base.getGameState() == GameState.PLAYING; k++)
				{
					//if k's cell is empty on the base board: creates a new game and plays k
					if(base.valueAt(k) == CellValue.EMPTY)
					{
						TicTacToeGame newGame = new TicTacToeGame(base, k);
						//adds game to listLevel if a duplicate of the game doesnt already exist in listLevel
						if  (!listLevel.stream().anyMatch(s->s.equals(newGame))){listLevel.add(newGame);}
					}
				}
			}
			if (! listLevel.isEmpty()) {// if listLevel is not empty that means that there are still more possible combination
				listAllGames.add(listLevel);
			}else
			{
				maxLevel = true; // once listLevel is empty we stop looping since we got all possible games that haven't ended
			}

			levels++;
		}
		return listAllGames;
	}
}
