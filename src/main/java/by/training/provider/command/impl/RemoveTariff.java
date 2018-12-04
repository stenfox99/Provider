package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.AdminService;

import javax.servlet.http.HttpServletRequest;

public class RemoveTariff implements Command {
    @Override
    public String execute(HttpServletRequest request) { //TODO EXCEPTION
        String tariffName = request.getParameter(FieldConst.TARIFF_NAME);
        AdminService service = new AdminService();
        try {
            service.removeTariff(tariffName);
        }catch (BusinessLogicException e){

        }
        return PagePath.mainPage;
    }
}
