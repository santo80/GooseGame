package it.infotn.goosegame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GooseGameServlet extends HttpServlet {
    IGame game;

    public GooseGameServlet(IGame game) {
        this.game = game;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().equals("/move")) {
            resp.getWriter().write(game.command("move "
                    + req.getParameter("name") + " "
                    + req.getParameter("dice1") + ", "
                    + req.getParameter("dice2") + " "));
        } else if (req.getPathInfo().equals("/players")) {
            resp.getWriter().write(game.command("add player "
                    + req.getParameter("name")));
        }
    }
}
