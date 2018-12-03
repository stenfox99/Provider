package by.training.provider.command.impl;

import by.training.provider.command.CommandType;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserPassword implements CommandType {

    @Override
    public String execute(HttpServletRequest request) {                     //TODO EXCEPTION
        int userId = (Integer)request.getSession().getAttribute(FieldConst.USER_ID);
        String password = request.getParameter(FieldConst.PASSWORD);
        try {
            UserService userService = new UserService();
            userService.updateUser(userId, password);
        }catch (BusinessLogicException e){

        }
        return PagePath.mainPage;
    }
}
