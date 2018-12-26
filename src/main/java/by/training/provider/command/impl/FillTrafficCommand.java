package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;

import javax.servlet.http.HttpServletRequest;

public class FillTrafficCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            new AdminService().fillTraffic();
            page = PagePath.USERS;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
