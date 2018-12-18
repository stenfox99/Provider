package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class IncreaseBalance implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        BigDecimal currentBalance = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.CURRENT_BALANCE)));
        BigDecimal balance = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.BALANCE)));
        UserService service = new UserService();
        try {
            service.increaseBalance(userId, currentBalance, balance);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.INCREASE_BALANCE_ERROR, e.getMessage());
        }
        return PagePath.PROFILE;
    }
}
