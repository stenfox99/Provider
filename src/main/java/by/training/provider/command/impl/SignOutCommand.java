package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.DirectionType;
import by.training.provider.command.Router;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new Router(PagePath.MAIN_PAGE, DirectionType.REDIRECT);
    }
}
