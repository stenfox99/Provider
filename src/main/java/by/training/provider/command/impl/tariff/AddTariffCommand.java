package by.training.provider.command.impl.tariff;

import by.training.provider.command.*;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddTariffCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page = new Router();
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        BigDecimal tariffPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.TARIFF_PRICE)));
        int monthTraffic = Integer.parseInt(request.getParameter(ParameterName.MONTH_TRAFFIC));
        String description = request.getParameter(ParameterName.DESCRIPTION);
        Tariff tariff = new Tariff(tariffName, tariffPrice, monthTraffic, description);
        AdminService adminService = new AdminService();
        try {
            adminService.addTariff(tariff);
            page.setDirectionType(DirectionType.REDIRECT);
        }catch (LogicException e){
            request.setAttribute(ParameterName.ERROR, e.getMessage());
            page.setDirectionType(DirectionType.FORWARD);
        }
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            tariffs = service.findAllTariffs();
            page.setPage(PagePath.TARIFFS);
        } catch (LogicException e) {
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
            request.setAttribute(ParameterName.ERROR, e);
        }
        List<?> printedTariffs = service.divideListOnPage(tariffs, 0);
        int countPage = service.pageCount(tariffs);
        request.setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.setAttribute(ParameterName.PRINTED_TARIFFS, printedTariffs);
        return page;
    }
}
