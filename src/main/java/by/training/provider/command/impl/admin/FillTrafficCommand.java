package by.training.provider.command.impl.admin;

import by.training.provider.command.*;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;

import javax.servlet.http.HttpServletRequest;

public class FillTrafficCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        try {
            new AdminService().fillTraffic();
            page = new Router(PagePath.USERS, DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
        }
        return page;
    }
}
