package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.DirectionType;
import by.training.provider.command.Router;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.MAIN_PAGE, DirectionType.FORWARD);
    }
}
