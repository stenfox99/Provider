package by.training.provider.servlet;

import by.training.provider.command.CommandMap;
import by.training.provider.command.Command;
import by.training.provider.command.DirectionType;
import by.training.provider.command.Router;
import by.training.provider.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String command = req.getParameter(COMMAND);
        Command commandType = CommandMap.valueOf(command.toUpperCase()).getCommand();
        Router page = commandType.execute(req);
        if (page.getDirectionType().equals(DirectionType.FORWARD)) {
            req.getRequestDispatcher(page.getPage()).forward(req, resp);
        }else{
//            resp.sendRedirect(req.getContextPath() + page.getPage());
            req.getRequestDispatcher(page.getPage()).forward(req, resp);
        }
    }

    @Override
    public void init(){
        ConnectionPool.getInstance();
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyConnections();
    }
}
