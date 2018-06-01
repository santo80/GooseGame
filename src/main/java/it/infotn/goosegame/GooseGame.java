package it.infotn.goosegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GooseGame implements IGame {

    public static final Integer CASELLAFINALE = 63;
    List<String> players = new ArrayList<String>();
    HashMap<String, Integer> game = new HashMap<String, Integer>();
    HashMap<String, HashMap<String[], String>> playerTrhows = new HashMap<String, HashMap<String[], String>>();

    private String addPlayer(String istruzione) {
        if (istruzione.toLowerCase().startsWith("add player ")) {
            if (!players.contains(getPlayerName(istruzione))) {
                players.add(getPlayerName(istruzione));
            } else {
                return getPlayerName(istruzione) + " already existing player";
            }
        }
        return getCurrentPlayers();
    }

    private String getPlayerName(String istruzione) {
        return istruzione.substring(11);
    }

    private String getCurrentPlayers() {
        return "players: " + players.toString().replace("[", "").replace("]", "");
    }

    private String movePlayer(String istruzione) {
        String player = istruzione.substring(5, 10);
        Integer posizione = calculateNewPlayerPosition(istruzione, player);
        String dice = istruzione.length() > 10 ? getPlayerName(istruzione) : getLastPlayerThrows(player);
        String result = player + " rolls " + dice + ". " + player + " moves from " + getLastPlayerMoves(player).replace("[", "").replace("]", "");
        if (posizione == CASELLAFINALE)
            result = playerWin(player, result);
        if (posizione > CASELLAFINALE) {
            result = playerBounce(player, posizione, result);
        }
        game.put(player, posizione);
        return result;
    }

    private String playerBounce(String player, Integer posizione, String result) {
        result = result.replace(posizione.toString(), CASELLAFINALE.toString());
        int bounce = posizione - CASELLAFINALE;
        result += ". " + player + " bounces! " + player + " return to " + (CASELLAFINALE - bounce);
        return result;
    }

    private String playerWin(String player, String result) {
        result += ". " + player + " Wins!!";
        return result;
    }

    private Integer calculateNewPlayerPosition(String istruzione, String player) {
        String partenza = getCurrentPlayerPosition(player) == 0 ? "Start" : getCurrentPlayerPosition(player).toString();
        Integer posizione = getCurrentPlayerPosition(player);
        String[] roll = (istruzione.length() > 10 ? getPlayerName(istruzione).replace(" ", "").split(",") : diceRoll());
        posizione += Integer.parseInt(roll[0]) + Integer.parseInt(roll[1]);

        HashMap<String[], String> rollMap = new HashMap<String[], String>();
        rollMap.put(roll, partenza + " to " + posizione);
        playerTrhows.put(player, rollMap);
        return posizione;
    }

    private String[] diceRoll() {
        Random r = new Random();
        return new String[]{Integer.toString(r.nextInt(5) + 1), Integer.toString(r.nextInt(5) + 1)};
    }

    private Integer getCurrentPlayerPosition(String player) {
        Integer posizione = 0;
        if (game.containsKey(player)) {
            posizione = game.get(player);
        }
        return posizione;
    }

    @Override
    public String command(String command) {
        if (command.startsWith("move")) {
            return movePlayer(command);
        } else if (command.startsWith("add")) {
            return addPlayer(command);
        }
        return "";
    }

    @Override
    public String getLastPlayerThrows(String player) {
        return String.join(", ", (String[]) playerTrhows.get(player).keySet().toArray()[0]);
    }

    @Override
    public String getLastPlayerMoves(String player) {
        return playerTrhows.get(player).values().toArray()[0].toString();
    }
}
