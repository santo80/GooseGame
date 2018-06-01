package it.infotn.goosegame;

public interface IGame {
    String command(String command);

    String getLastPlayerThrows(String player);

    String getLastPlayerMoves(String pippo);
}
