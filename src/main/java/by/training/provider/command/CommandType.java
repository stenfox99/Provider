package by.training.provider.command;

import javax.servlet.http.HttpServletRequest;

public interface CommandType {
    String execute(HttpServletRequest request);
}
