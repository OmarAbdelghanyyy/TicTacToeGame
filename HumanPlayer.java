import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class HumanPlayer implements  Player{
    private boolean checkplayerentry = true;
    @Override
    public void play(TicTacToeGame game) {
        // YOUR CODE HERE
        try
        {
            // statement(s) that might cause exception
            out.print("Player 1’s turn.\n");
            System.out.println(game);
            out.print(game.nextCellValue()+" to play : " );
            Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
            int choice= sc.nextInt();
            int value = choice-1;
            game.play(value);

            if(game.getGameState().equals(GameState.OWIN) ||game.getGameState().equals(GameState.XWIN) ||game.getGameState().equals(GameState.DRAW)){
                out.println(game);
                System.out.println("Result: " + game.getGameState());
            }

        }catch (Exception e){
            System.out.println("Illegal position or cellvalue not empty at this position");
            //throw new exception just to ensure that the player would not be increment until player provide good input check line 95 tictactoe
            throw  new RuntimeException("Illegal position or cellvalue not empty at this position");
        }
    }
}
