package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToProfile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute(ParameterName.USER_ID).toString());
        CommonService commonService = new CommonService();
        UserData userData = new UserData();
        try {
            userData = commonService.findUserData(userId);
        } catch (LogicException e) {
            //TODO EXCEPTION
        }
        request.setAttribute(ParameterName.USER_DATA, userData);
        return PagePath.PROFILE;
    }
}
