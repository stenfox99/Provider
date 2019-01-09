package by.training.provider.command.impl.tariff;

import by.training.provider.command.*;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RemoveTariffCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        AdminService adminService = new AdminService();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            adminService.removeTariff(tariffName);
            tariffs = service.findAllTariffs();
            page = new Router(PagePath.TARIFFS, DirectionType.FORWARD);
        }catch (LogicException e){
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.FORWARD);
        }
        List<?> printedTariffs = service.divideListOnPage(tariffs, 0);
        int countPage = service.pageCount(tariffs);
        request.setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.setAttribute(ParameterName.PRINTED_TARIFFS, printedTariffs);
        return page;
    }
}
