package by.training.provider.command.impl.user;

import by.training.provider.command.*;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ConnectToTariffCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        UserService service = new UserService();
        UserData userData = new UserData();
        try {
            service.changeTariff(userId, tariffName);
            userData = service.findUserData(userId);
            page = new Router(PagePath.PROFILE, DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
        }
        request.getSession().setAttribute(ParameterName.USER_DATA, userData);
        return page;
    }
}
