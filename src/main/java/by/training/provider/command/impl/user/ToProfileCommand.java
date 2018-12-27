package by.training.provider.command.impl.user;

import by.training.provider.command.*;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute(ParameterName.USER_ID).toString());
        UserService service = new UserService();
        UserData userData = new UserData();
        try {
            userData = service.findUserData(userId);
            page = new Router(PagePath.PROFILE, DirectionType.FORWARD);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.FORWARD);
        }
        request.getSession().setAttribute(ParameterName.USER_DATA, userData);
        return page;
    }
}
