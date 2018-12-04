package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.User;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignIn implements Command {
    @Override
    public String execute(HttpServletRequest request) {             //TODO EXCEPTION
        String login = request.getParameter(FieldConst.LOGIN);
        String password = request.getParameter(FieldConst.PASSWORD);
        UserService userService = new UserService();
        String page = "";
        try {
            Optional<User> user = userService.findUser(login, password);
            if (user.isPresent()) {
                page = PagePath.mainPage;
                request.setAttribute(FieldConst.ERROR, "Incorrect login or password");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(FieldConst.LOGIN, user.get().getLogin());
                session.setAttribute(FieldConst.ROLE, user.get().getUserType().getUserType());
                page = PagePath.mainPage;
            }
        }catch (BusinessLogicException e){

        }
        return page;
    }
}
