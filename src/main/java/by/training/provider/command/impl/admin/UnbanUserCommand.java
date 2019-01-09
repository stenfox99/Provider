package by.training.provider.command.impl.admin;

import by.training.provider.command.*;
import by.training.provider.entity.User;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UnbanUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        String login = request.getParameter(ParameterName.LOGIN);
        AdminService adminService = new AdminService();
        List<User> users = new ArrayList<>();
        try {
            adminService.unbanUser(login);
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
