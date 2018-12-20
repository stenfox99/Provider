package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.CommonService;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute(ParameterName.USER_ID).toString());
        UserService service = new UserService();
        UserData userData = new UserData();
        try {
            userData = service.findUserData(userId);
            page = PagePath.PROFILE;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        request.getSession().setAttribute(ParameterName.USER_DATA, userData);
        return page;
    }
}
