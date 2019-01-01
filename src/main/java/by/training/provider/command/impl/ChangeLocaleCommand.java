package by.training.provider.command.impl;

import by.training.provider.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(ParameterName.LANGUAGE);
        session.setAttribute(ParameterName.LOCALE, locale);
        return new Router(request.getSession().getAttribute(ParameterName.PAGE).toString(), DirectionType.FORWARD);
    }
}
