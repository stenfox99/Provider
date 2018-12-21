package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadImage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        Part part;
        try {
            part = request.getPart(ParameterName.IMAGE);
            UserService service = new UserService();
            service.uploadImage(userId, part);
            page = PagePath.PROFILE;
        } catch (IOException | ServletException e) {
            request.setAttribute(ParameterName.IMAGE_ERROR, e.getMessage());
            page = PagePath.PROFILE;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        return page;
    }
}
