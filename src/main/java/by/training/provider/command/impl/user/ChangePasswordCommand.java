package by.training.provider.command.impl.user;

import by.training.provider.command.*;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router(PagePath.PROFILE, null);
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        String password = request.getParameter(ParameterName.PASSWORD);
        String password2 = request.getParameter(ParameterName.SECOND_PASSWORD);
        UserService service = new UserService();
        try {
            service.changePassword(userId, password, password2);
            page.setDirectionType(DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.CHANGE_PASSWORD_ERROR, e.getMessage());
            page.setDirectionType(DirectionType.FORWARD);
        }
        return new Router(PagePath.PROFILE, DirectionType.REDIRECT);
    }
}
