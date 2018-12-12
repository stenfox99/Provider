package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;
import by.training.provider.entity.User;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignIn implements Command {
    @Override
    public String execute(HttpServletRequest request) {             //TODO SEND ERROR PAGE
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        UserService userService = new UserService();
        String page = "";
        try {
            Optional<User> user = userService.findUser(login, password);
            if (user.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute(ParameterName.LOGIN, user.get().getLogin());
                session.setAttribute(ParameterName.ROLE, user.get().getUserType().getUserType());
                page = PagePath.MAIN_PAGE;
            } else {
                request.setAttribute(ParameterName.ERROR, "Incorrect login or password");
                page = PagePath.MAIN_PAGE;
            }
        }catch (LogicException e){

        }
        return page;
    }
}
