package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RemoveDiscount implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(ParameterName.DISCOUNT_NAME);
        Discount discount = new Discount(name);
        AdminService adminService = new AdminService();
        try {
            adminService.removeDiscount(discount);
        } catch (LogicException e) {
            //TODO EXCEPTION
        }

        List<Discount> discounts = new ArrayList<>();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            discounts = service.findAllDiscounts();
            tariffs = service.findAllTariffs();
        } catch (DaoException e) {
            //TODO EXCEPTION
        }
        List<Discount> printedDiscounts = service.divideListOnPage(discounts, 0);
        if (discounts.size() % ParameterName.COUNT_ON_PAGE == 0) {
            request.setAttribute("countPage", discounts.size() / ParameterName.COUNT_ON_PAGE);
        } else {
            request.setAttribute("countPage", discounts.size() / ParameterName.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedDiscounts", printedDiscounts);
        request.setAttribute("TARIFFS", tariffs);
        return PagePath.DISCOUNTS;
    }
}
