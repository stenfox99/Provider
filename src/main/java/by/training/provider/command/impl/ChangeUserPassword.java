package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;
import by.training.provider.util.Encrypt;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserPassword implements Command {

    @Override
    public String execute(HttpServletRequest request) {                     //TODO EXCEPTION
        String login = request.getSession().getAttribute(FieldConst.LOGIN).toString();
        String password = request.getParameter(FieldConst.PASSWORD);
        UserService userService = new UserService();
        String encryptPassword = Encrypt.encrypt(password);
        try {
            userService.updateUser(login, encryptPassword);
        }catch (LogicException e){

        }
        return PagePath.mainPage;
    }
}
