package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocale implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(ParameterName.LOCALE);
        System.out.println(locale);
        session.setAttribute("locale", locale);
        return PagePath.MAIN_PAGE;
    }
}
