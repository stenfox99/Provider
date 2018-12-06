package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.AdminService;

import javax.servlet.http.HttpServletRequest;

public class AddUser implements Command {
    private static final String ADMIN = "admin";

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(FieldConst.LOGIN);          //TODO EXCEPTION
        String password = request.getParameter(FieldConst.PASSWORD);
        String userType = request.getParameter(FieldConst.USER_TYPE);
        AdminService service = new AdminService();
        try {
            if (userType.equals(ADMIN)) {
                UserType type = new UserType(1);
                User user = new User(1, login, password, type);
                service.addAdmin(user);
            } else {
                UserType type = new UserType(2);
                User user = new User(1, login, password, type);
                service.addUser(user);
            }
        } catch (BusinessLogicException e) {

        }
        return PagePath.mainPage;
    }
}
