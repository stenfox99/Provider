package by.training.provider.command.impl;

import by.training.provider.command.*;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadImage implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
        Part part;
        try {
            part = request.getPart(ParameterName.IMAGE);
            UserService service = new UserService();
            String image = service.uploadImage(userId, part);
            UserData userData = (UserData)request.getSession().getAttribute(ParameterName.USER_DATA);
            userData.setPhoto(image);
            request.getSession().setAttribute(ParameterName.USER_DATA, userData);
            page = new Router(PagePath.PROFILE, DirectionType.REDIRECT);
        } catch (IOException | ServletException e) {
            request.setAttribute(ParameterName.IMAGE_ERROR, e.getMessage());
            page = new Router(PagePath.PROFILE, DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
        }
        return page;
    }
}
