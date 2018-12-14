package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;
import by.training.provider.util.Encrypt;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserPassword implements Command {

    @Override
    public String execute(HttpServletRequest request) {                     //TODO EXCEPTION
        String login = request.getSession().getAttribute(ParameterName.LOGIN).toString();
        String password = request.getParameter(ParameterName.PASSWORD);
        UserService userService = new UserService();
        String encryptPassword = Encrypt.encrypt(password);
        try {
            userService.updateUserPassword(login, encryptPassword);
        }catch (LogicException e){

        }
        return PagePath.MAIN_PAGE;
    }
}
