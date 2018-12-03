package by.training.provider.servlet;

import by.training.provider.command.CommandMap;
import by.training.provider.command.CommandType;
import by.training.provider.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccesRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccesRequest(req,resp);
    }

    private void proccesRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String command = req.getParameter(COMMAND);
        CommandType commandType = CommandMap.valueOf(command.toUpperCase()).getCommand();
        String page = commandType.execute(req);
        req.getRequestDispatcher(req.getContextPath() + page).forward(req, resp);
    }

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        ConnectionPool.getInstance();
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//        ConnectionPool.getInstance().destroyConnections();
//    }
}
