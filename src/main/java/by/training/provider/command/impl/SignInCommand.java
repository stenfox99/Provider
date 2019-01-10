package by.training.provider.command.impl;

import by.training.provider.command.*;
import by.training.provider.entity.User;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        UserService userService = new UserService();
        try {
            Optional<User> user = userService.findUser(login, password);
            if (user.isPresent()) {
                if (user.get().isBan()){
                    request.setAttribute(ParameterName.ERROR, "This user was banned");
                    page = new Router(request.getSession().getAttribute(ParameterName.PAGE).toString(), DirectionType.FORWARD);
                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute(ParameterName.LOGIN, user.get().getLogin());
                    session.setAttribute(ParameterName.USER_ID, user.get().getUserId());
                    session.setAttribute(ParameterName.ROLE, user.get().getUserType().getUserTypeValue());
                    page = new Router(request.getSession().getAttribute(ParameterName.PAGE).toString(), DirectionType.REDIRECT);
                }
            } else {
                request.setAttribute(ParameterName.ERROR, "Incorrect login or password");
                page = new Router(request.getSession().getAttribute(ParameterName.PAGE).toString(), DirectionType.FORWARD);
            }
        }catch (LogicException e){
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
        }
        return page;
    }
}
