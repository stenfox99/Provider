package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class RemoveTariff implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter(FieldConst.TARIFF_NAME);

        return PagePath.mainPage;
    }
}
