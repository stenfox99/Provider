package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(ParameterName.LANGUAGE);
        System.out.println(locale);
        session.setAttribute(ParameterName.LOCALE, locale);
        return PagePath.MAIN_PAGE;
    }
}
