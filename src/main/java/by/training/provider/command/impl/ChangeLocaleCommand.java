package by.training.provider.command.impl;

import by.training.provider.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(ParameterName.LANGUAGE);
        System.out.println(locale);
        session.setAttribute(ParameterName.LOCALE, locale);
        return new Router(PagePath.MAIN_PAGE, DirectionType.FORWARD);
    }
}
