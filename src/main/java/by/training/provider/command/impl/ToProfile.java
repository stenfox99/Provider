package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ToProfile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PROFILE;
    }
}
