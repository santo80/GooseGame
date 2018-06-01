package it.infotn.goosegame;

public class GooseGameDummy extends GooseGame {
    @Override
    public String command(String command) {
        if (command.equals("add player Pippo")) {
            return "players: Pippo";
        } else if (command.equals("add player Pluto")) {
            return "players: Pippo, Pluto";
        } else if ( command.equals("move Pippo 4, 5 ")){
            return "Pippo rolls 4, 5. Pippo moves from Start to 9";
        }
        return "";
    }
}