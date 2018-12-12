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

public class UpdateTariff implements Command {              //TODO EXCEPTION
    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter(ParameterName.TARIFF_PRICE)));
        int monthTraffic = Integer.parseInt(request.getParameter(ParameterName.MONTH_TRAFFIC));
        String description = request.getParameter(ParameterName.DESCRIPTION);
        Tariff tariff = new Tariff(tariffName, price, monthTraffic, description);
        AdminService adminService = new AdminService();
        try{
            adminService.updateTariff(tariff);
        }catch (LogicException e){
            request.setAttribute("error", e.getMessage());
        }
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            tariffs = service.findAllTariffs();
        } catch (DaoException e) {
                                                                //TODO EXCEPTION
        }
        List<Tariff> printedTariffs = service.divideListOnPage(tariffs, 0);
        if (tariffs.size() % ParameterName.COUNT_ON_PAGE == 0){
            request.setAttribute("countPage", tariffs.size() / ParameterName.COUNT_ON_PAGE);
        }else {
            request.setAttribute("countPage", tariffs.size() / ParameterName.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedTariffs", printedTariffs);
        return PagePath.TARIFFS;
    }
}
