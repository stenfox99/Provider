package by.training.provider.command.impl.user;

import by.training.provider.command.*;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileInfoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String patronymic = request.getParameter(ParameterName.PATRONYMIC);
        String email = request.getParameter(ParameterName.EMAIL);
        String phone = request.getParameter(ParameterName.PHONE);
        UserData newUserData = new UserData(firstName, lastName, patronymic, email, phone, userId);
        UserService service = new UserService();
        try {
            service.updateProfileData(newUserData);
            page.setDirectionType(DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.CHANGE_INFO_PROFILE_ERROR, e.getMessage());
            page.setDirectionType(DirectionType.FORWARD);
        }
        UserData userData = new UserData();
        try {
            userData = service.findUserData(userId);
            page.setPage(PagePath.PROFILE);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page.setPage(PagePath.ERROR);
        }
        request.getSession().setAttribute(ParameterName.USER_DATA, userData);
        return page;
    }
}
