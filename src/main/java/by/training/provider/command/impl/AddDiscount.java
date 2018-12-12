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
import by.training.provider.util.DateConverter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddDiscount implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String discountName = request.getParameter(ParameterName.DISCOUNT_NAME);
        String tariffName = request.getParameter(ParameterName.TARIFF_NAME);
        int discountValue = Integer.parseInt(request.getParameter(ParameterName.DISCOUNT));
        String description = request.getParameter(ParameterName.DESCRIPTION);
        Date beginningDate = null;
        Date endDate = null;
        try {
            beginningDate = DateConverter.toDate(request.getParameter(ParameterName.BEGINNING_DATE));
            endDate = DateConverter.toDate(request.getParameter(ParameterName.END_DATE));
        } catch (LogicException e) {
            //TODO EXCEPTION
        }
        Tariff tariff = new Tariff(tariffName);
        Discount discount = new Discount(discountName, discountValue, description, beginningDate, endDate, tariff);
        AdminService adminService = new AdminService();
        try {
            adminService.addDiscount(discount);
        } catch (LogicException e) {
            request.setAttribute("error", e.getMessage());
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
        int countPage = service.pageCount(discounts);
        request.setAttribute("countPage", countPage);
        request.setAttribute("printedDiscounts", printedDiscounts);
        request.setAttribute("TARIFFS", tariffs);
        return PagePath.DISCOUNTS;
    }
}
