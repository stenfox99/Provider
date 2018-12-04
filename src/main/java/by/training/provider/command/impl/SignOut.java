package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return PagePath.mainPage;
    }
}
