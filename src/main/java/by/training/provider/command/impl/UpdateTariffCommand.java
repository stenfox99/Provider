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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UpdateTariffCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.TARIFF_PRICE)));
        int monthTraffic = Integer.parseInt(request.getParameter(ParameterName.MONTH_TRAFFIC));
        String description = request.getParameter(ParameterName.DESCRIPTION);
        Tariff tariff = new Tariff(tariffName, price, monthTraffic, description);
        AdminService adminService = new AdminService();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try{
            adminService.updateTariff(tariff);
        }catch (LogicException e){
            request.setAttribute(ParameterName.ERROR, e.getMessage());
        }
        try {
            tariffs = service.findAllTariffs();
            page = PagePath.TARIFFS;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        List<?> printedTariffs = service.divideListOnPage(tariffs, 0);
        int countPage = service.pageCount(tariffs);
        request.setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.setAttribute(ParameterName.PRINTED_TARIFFS, printedTariffs);
        return page;
    }
}
