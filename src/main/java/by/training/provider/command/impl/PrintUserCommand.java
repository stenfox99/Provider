package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int pageNumber = Integer.parseInt(request.getParameter(ParameterName.PAGE_NUMBER));
        List<User> users = new ArrayList<>();
        List<UserType> userTypes = new ArrayList<>();
        AdminService adminService = new AdminService();
        try {
            users = adminService.findAllUser();
            userTypes = adminService.findAllUserType();
            page = PagePath.USERS;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        CommonService service = new CommonService();
        List<?> printedUsers = service.divideListOnPage(users, pageNumber);
        int countPage = service.pageCount(users);
        request.getSession().setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.getSession().setAttribute(ParameterName.USER_TYPE, userTypes);
        request.getSession().setAttribute(ParameterName.PRINTED_USERS, printedUsers);
        return page;
    }
}
