package it.infotn.goosegame;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {


    private static Server server;

    public static void main(String[] args)  throws Exception {
        start(new GooseGame());
    }

    public static void start(GooseGame gooseGame) throws Exception {

        server = new Server(9001);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new GooseGameServlet(gooseGame)),"/goose/*");
        server.setHandler(handler);
        server.start();
    }

    public static void stop() throws Exception {
        server.stop();
    }
}
