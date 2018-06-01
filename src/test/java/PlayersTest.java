import it.infotn.goosegame.GooseGame;
import it.infotn.goosegame.IGame;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayersTest {


    @Test
    public void addPlayerPippo() throws Exception {
        IGame game = new GooseGame();
        assertEquals("players: Pippo", game.command("add player Pippo"));
    }

    @Test
    public void addPlayerPippoPluto() throws Exception {
        IGame game = new GooseGame();
        game.command("add player Pippo");
        assertEquals("players: Pippo, Pluto", game.command("add player Pluto"));
    }

    @Test
    public void duplicatePlayer() throws Exception {
        IGame game = new GooseGame();
        game.command("add player Pippo");
        assertEquals("Pippo already existing player", game.command("add player Pippo"));
    }

    @Test
    public void moveAPlayer() throws Exception {
        IGame game = new GooseGame();
        game.command("add player Pippo");
        game.command("add player Pluto");
        assertEquals("Pippo rolls 4, 2. Pippo moves from Start to 6", game.command("move Pippo 4, 2"));
        assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4", game.command("move Pluto 2, 2"));
        assertEquals("Pippo rolls 2, 3. Pippo moves from 6 to 11", game.command("move Pippo 2, 3"));
    }

    @Test
    public void win() throws Exception {
        IGame game = new GooseGame();
        game.command("add player Pippo");
        assertEquals("Pippo rolls 30, 30. Pippo moves from Start to 60", game.command("move Pippo 30, 30"));
        assertEquals("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!", game.command("move Pippo 1, 2"));
    }

    @Test
    public void diceShooting() throws Exception {
        IGame game = new GooseGame();
        game.command("add player Pippo");
        assertEquals("Pippo rolls 30, 30. Pippo moves from Start to 60", game.command("move Pippo 30, 30"));
        assertEquals("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo return to 61", game.command("move Pippo 3, 2"));
    }

    @Test
    public void diceRoll() throws Exception {
        IGame game = new GooseGame();
        game.command("add player Pippo");
        game.command("move Pippo 2, 2");
        String move_pippo = game.command("move Pippo");
        String lastPlayerThrows = game.getLastPlayerThrows("Pippo");
        String lastPlayerMoves = game.getLastPlayerMoves("Pippo");
        assertEquals("Pippo rolls " + lastPlayerThrows + ". Pippo moves from " + lastPlayerMoves, move_pippo);
    }

}
