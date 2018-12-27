package by.training.provider.command.impl.discount;

import by.training.provider.command.*;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.AdminService;
import by.training.provider.service.CommonService;
import by.training.provider.util.DateConverter;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddDiscountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router page    ;
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
            request.setAttribute(ParameterName.ERROR, e.getMessage());
        }
        Tariff tariff = new Tariff(tariffName);
        Discount discount = new Discount(discountName, discountValue, description, beginningDate, endDate, tariff);
        AdminService adminService = new AdminService();
        try {
            adminService.addDiscount(discount);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e.getMessage());
        }

        List<Discount> discounts = new ArrayList<>();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            discounts = service.findAllDiscounts();
            tariffs = service.findAllTariffs();
            page = new Router(PagePath.DISCOUNTS, DirectionType.REDIRECT);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.REDIRECT);
        }
        List<?> printedDiscounts = service.divideListOnPage(discounts, 0);
        int countPage = service.pageCount(discounts);
        request.setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.setAttribute(ParameterName.PRINTED_DISCOUNTS, printedDiscounts);
        request.setAttribute(ParameterName.TARIFFS, tariffs);
        return page;
    }
}
