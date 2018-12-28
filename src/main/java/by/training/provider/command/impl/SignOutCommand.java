package by.training.provider.command.impl;

import by.training.provider.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentPage = request.getSession().getAttribute(ParameterName.PAGE).toString();
        session.invalidate();
        return new Router(currentPage, DirectionType.REDIRECT);
    }
}
