package by.training.provider.listener;

import by.training.provider.command.ParameterName;
import by.training.provider.exception.LogicException;
import by.training.provider.service.UserService;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class DecreaseTrafficListener implements ServletRequestListener {
    private static final String USER = "user";

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        if (request.getSession().getAttribute(ParameterName.USER_ID) != null &&
                request.getSession().getAttribute(ParameterName.ROLE).toString().equals(USER)) { //TODO CHECK NULL
            int userId = Integer.parseInt(request.getSession().getAttribute(ParameterName.USER_ID).toString());
            try {
                new UserService().decreaseTraffic(userId);
            } catch (LogicException e) {
                //TODO EXCEPTION
            }
        }
    }
}
