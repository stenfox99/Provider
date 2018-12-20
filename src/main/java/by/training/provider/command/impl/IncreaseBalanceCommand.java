package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class IncreaseBalanceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        BigDecimal currentBalance = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.CURRENT_BALANCE)));
        BigDecimal balance = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.BALANCE)));
        UserService userService = new UserService();
        try {
            userService.increaseBalance(userId, currentBalance, balance);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.INCREASE_BALANCE_ERROR, e.getMessage());
        }
        UserData userData = new UserData();
        try {
            userData = userService.findUserData(userId);
            page = PagePath.PROFILE;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        request.getSession().setAttribute(ParameterName.USER_DATA, userData);
        return page;
    }
}
