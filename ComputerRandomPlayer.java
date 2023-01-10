import java.util.Random;

import static java.lang.System.out;

public class ComputerRandomPlayer implements Player{
    private boolean checkcomputerentry = true;
    @Override
    public void play(TicTacToeGame game) {

        // YOUR CODE HERE
        try
        {
            // statement(s) that might cause exception
            //checkcomputerentry to print one time the message of the computer turn
            if (checkcomputerentry)
                out.print("Computer’s Player 2’s turn.\n");
            Random rand = new Random();
            int random=0;
                //generate random
                random= rand.nextInt(game.columns* game.lines);
                int value = random;
                game.play(value);

                if(game.getGameState().equals(GameState.OWIN) ||game.getGameState().equals(GameState.XWIN) ||game.getGameState().equals(GameState.DRAW)){
                    out.println(game);
                    System.out.println("Result: " + game.getGameState());
                }
            this.checkcomputerentry = true;
        }catch (Exception e){
            this.checkcomputerentry = false;
            throw  new RuntimeException(e.getMessage());
        }


    }

}
