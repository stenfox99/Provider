package by.training.provider.command.impl.admin;

import by.training.provider.command.*;
import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddUserCommand implements Command {
    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final int ADMIN_TYPE = 1;
    private static final int USER_TYPE = 2;

    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String userType = request.getParameter(ParameterName.USER_TYPE);
        AdminService adminService = new AdminService();
        try {
            if (userType.equals(ADMIN)) {
                UserType type = new UserType(ADMIN_TYPE);
                User user = new User(login, password, type);
                adminService.addAdmin(user);
            } if (userType.equals(USER)) {
                UserType type = new UserType(USER_TYPE);
                User user = new User(login, password, type);
                adminService.addUser(user);
            }else{
                request.setAttribute(ParameterName.ERROR, "Incorrect input data");
            }
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e.getMessage());
        }
        List<User> users = new ArrayList<>();
        try {
            users = adminService.findAllUser();
            page = new Router(PagePath.USERS, DirectionType.FORWARD);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
        }
        CommonService service = new CommonService();
        List<?> printedUsers = service.divideListOnPage(users, 0);
        int countPage = service.pageCount(users);
        request.getSession().setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.getSession().setAttribute(ParameterName.PRINTED_USERS, printedUsers);
        return page;
    }
}
