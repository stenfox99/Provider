package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.exception.DaoException;
import by.training.provider.service.AdminService;
import by.training.provider.service.WithoutRoleService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UpdateTariff implements Command {              //TODO EXCEPTION
    @Override
    public String execute(HttpServletRequest request) {
        String tariffName = request.getParameter(FieldConst.TARIFF_NAME);
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter(FieldConst.TARIFF_PRICE)));
        int monthTraffic = Integer.parseInt(request.getParameter(FieldConst.MONTH_TRAFFIC));
        String description = request.getParameter(FieldConst.DESCRIPTION);
        Tariff tariff = new Tariff(tariffName, price, monthTraffic, description);
        AdminService adminService = new AdminService();
        try{
            adminService.updateTariff(tariff);
        }catch (BusinessLogicException e){
            request.setAttribute("error", e.getMessage());
        }
        List<Tariff> tariffs = new ArrayList<>();
        WithoutRoleService service = new WithoutRoleService();
        try {
            tariffs = service.findTariffs();
        } catch (DaoException e) {
                                                                //TODO EXCEPTION
        }
        List<Tariff> printedTariffs = service.getListOnPage(tariffs, 0);
        if (tariffs.size() % FieldConst.COUNT_ON_PAGE == 0){
            request.setAttribute("countPage", tariffs.size() / FieldConst.COUNT_ON_PAGE);
        }else {
            request.setAttribute("countPage", tariffs.size() / FieldConst.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedTariffs", printedTariffs);
        return PagePath.tariffs;
    }
}
