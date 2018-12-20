package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        String password = request.getParameter(ParameterName.PASSWORD);
        String password2 = request.getParameter(ParameterName.SECOND_PASSWORD);
        UserService service = new UserService();
        try {
            service.changePassword(userId, password, password2);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.CHANGE_PASSWORD_ERROR, e.getMessage());
        }
        return PagePath.PROFILE;
    }
}
