package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RemoveDiscount implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String name = request.getParameter(ParameterName.DISCOUNT_NAME);
        Discount discount = new Discount(name);
        AdminService adminService = new AdminService();
        List<Discount> discounts = new ArrayList<>();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            adminService.removeDiscount(discount);
            discounts = service.findAllDiscounts();
            tariffs = service.findAllTariffs();
            page = PagePath.DISCOUNTS;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        List<Discount> printedDiscounts = service.divideListOnPage(discounts, 0);
        int countPage = service.pageCount(discounts);
        request.setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.setAttribute(ParameterName.PRINTED_DISCOUNTS, printedDiscounts);
        request.setAttribute(ParameterName.TARIFFS, tariffs);
        return page;
    }
}
