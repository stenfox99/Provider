package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.exception.DaoException;
import by.training.provider.service.AdminService;
import by.training.provider.service.WithoutRoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RemoveTariff implements Command {
    @Override
    public String execute(HttpServletRequest request) { //TODO EXCEPTION
        String tariffName = request.getParameter(FieldConst.TARIFF_NAME);
        AdminService adminService = new AdminService();
        try {
            adminService.removeTariff(tariffName);
        }catch (LogicException e){

        }
        List<Tariff> tariffs = new ArrayList<>();
        WithoutRoleService service = new WithoutRoleService();
        try {
            tariffs = service.findAllTariffs();
        } catch (DaoException e) {
            //TODO EXCEPTION
        }
        List<Tariff> printedTariffs;                                                                //TODO LOGIC IN COMMAND
        if (FieldConst.COUNT_ON_PAGE >= tariffs.size()) {
            printedTariffs = tariffs.subList(0, tariffs.size());
        } else {
            printedTariffs = tariffs.subList(0, FieldConst.COUNT_ON_PAGE);
        }
        if (tariffs.size() % FieldConst.COUNT_ON_PAGE == 0){
            request.setAttribute("countPage", tariffs.size() / FieldConst.COUNT_ON_PAGE);
        }else {
            request.setAttribute("countPage", tariffs.size() / FieldConst.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedTariffs", printedTariffs);
        return PagePath.tariffs;
    }
}
