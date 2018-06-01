import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import it.infotn.goosegame.Application;
import it.infotn.goosegame.GooseGame;
import it.infotn.goosegame.GooseGameDummy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerServletTest {

    @BeforeClass
    public static void  setup() throws Exception {
        Application.start(new GooseGameDummy());
    }

    @Test
    public void addPlayerPippo() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/goose/players?name=Pippo").asString();
        assertEquals("players: Pippo", response.getBody());
    }

    @Test
    public void addPlayerPluto() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/goose/players?name=Pluto").asString();
        assertEquals("players: Pippo, Pluto", response.getBody());
    }

    @Test
    public void movePlayer() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/goose/move?name=Pippo&dice1=4&dice2=5").asString();
        assertEquals("Pippo rolls 4, 5. Pippo moves from Start to 9", response.getBody());
    }

    @AfterClass
    public static void  endAll() throws Exception {
        Application.stop();
    }
}
