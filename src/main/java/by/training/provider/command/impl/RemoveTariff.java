package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.exception.DaoException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RemoveTariff implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        AdminService adminService = new AdminService();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            adminService.removeTariff(tariffName);
            tariffs = service.findAllTariffs();
            page = PagePath.TARIFFS;
        }catch (LogicException e){
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        List<Tariff> printedTariffs = service.divideListOnPage(tariffs, 0);
        int countPage = service.pageCount(tariffs);
        request.setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.setAttribute(ParameterName.PRINTED_TARIFFS, printedTariffs);
        return PagePath.TARIFFS;
    }
}
