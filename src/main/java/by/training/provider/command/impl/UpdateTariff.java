package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class UpdateTariff implements Command {              //TODO EXCEPTION
    @Override
    public String execute(HttpServletRequest request) {
        int tariffId = Integer.parseInt(request.getParameter(FieldConst.TARIFF_ID));
        String tariffName = request.getParameter(FieldConst.TARIFF_NAME);
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter(FieldConst.TARIFF_PRICE)));
        String description = request.getParameter(FieldConst.DESCRIPTION);
        Tariff tariff = new Tariff(tariffId, tariffName, price, description);
        AdminService service = new AdminService();
        try{
            service.updateTariff(tariff);
        }catch (BusinessLogicException e){

        }
        return PagePath.mainPage;
    }
}
