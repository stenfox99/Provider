package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.WithoutRoleService;
import by.training.provider.util.DateConverter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddDiscount implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String discountName = request.getParameter(FieldConst.DISCOUNT_NAME);
        String tariffName = request.getParameter(FieldConst.TARIFF_NAME);
        int discountValue = Integer.parseInt(request.getParameter(FieldConst.DISCOUNT));
        String description = request.getParameter(FieldConst.DESCRIPTION);
        Date beginningDate = null;
        Date endDate = null;
        try {
            beginningDate = DateConverter.toDate(request.getParameter(FieldConst.BEGINNING_DATE));
            endDate = DateConverter.toDate(request.getParameter(FieldConst.END_DATE));
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
        WithoutRoleService service = new WithoutRoleService();
        try {
            discounts = service.findAllDiscounts();
            tariffs = service.findAllTariffs();
        } catch (DaoException e) {
            //TODO EXCEPTION
        }
        List<Discount> printedDiscounts;                                                                //TODO LOGIC IN COMMAND
        if (FieldConst.COUNT_ON_PAGE >= discounts.size()) {
            printedDiscounts = discounts.subList(0, discounts.size());
        } else {
            printedDiscounts = discounts.subList(0, FieldConst.COUNT_ON_PAGE);
        } //TODO LOGIC IN COMMAND
        if (discounts.size() % FieldConst.COUNT_ON_PAGE == 0) {
            request.setAttribute("countPage", discounts.size() / FieldConst.COUNT_ON_PAGE);
        } else {
            request.setAttribute("countPage", discounts.size() / FieldConst.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedDiscounts", printedDiscounts);
        request.setAttribute("tariffs", tariffs);
        return PagePath.discounts;
    }
}
