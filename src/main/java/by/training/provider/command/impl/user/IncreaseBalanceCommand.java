package by.training.provider.command.impl.user;

import by.training.provider.command.*;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class IncreaseBalanceCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        BigDecimal currentBalance = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.CURRENT_BALANCE)));
        BigDecimal balance = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.BALANCE)));
        UserService userService = new UserService();
        try {
            userService.increaseBalance(userId, currentBalance, balance);
            page.setDirectionType(DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.INCREASE_BALANCE_ERROR, e.getMessage());
            page.setDirectionType(DirectionType.FORWARD);
        }
        UserData userData = new UserData();
        try {
            userData = userService.findUserData(userId);
            page.setPage(PagePath.PROFILE);
        } catch (LogicException e) {
            page.setPage(PagePath.ERROR);
            request.setAttribute(ParameterName.ERROR, e);
        }
        request.getSession().setAttribute(ParameterName.USER_DATA, userData);
        return page;
    }
}
