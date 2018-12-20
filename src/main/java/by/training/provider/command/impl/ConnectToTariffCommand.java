package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ConnectToTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        UserService service = new UserService();
        UserData userData = new UserData();
        try {
            service.changeTariff(userId, tariffName);
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
