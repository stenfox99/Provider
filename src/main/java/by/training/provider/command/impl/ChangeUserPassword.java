package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserPassword implements Command {

    @Override
    public String execute(HttpServletRequest request) {                     //TODO EXCEPTION
        String login = request.getSession().getAttribute(FieldConst.LOGIN).toString();
        String password = request.getParameter(FieldConst.PASSWORD);
        try {
            UserService userService = new UserService();
            userService.updateUser(login, password);
        }catch (BusinessLogicException e){

        }
        return PagePath.mainPage;
    }
}
