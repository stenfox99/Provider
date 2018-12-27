package by.training.provider.command.impl.discount;

import by.training.provider.command.*;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintDiscountCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        int pageNumber = Integer.parseInt(request.getParameter(ParameterName.PAGE_NUMBER));
        List<Discount> discounts = new ArrayList<>();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            discounts = service.findAllDiscounts();
            tariffs = service.findAllTariffs();
            page = new Router(PagePath.DISCOUNTS, DirectionType.FORWARD);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.FORWARD);
        }
        List<?> printedDiscounts = service.divideListOnPage(discounts, pageNumber);
        int countPage = service.pageCount(discounts);
        request.getSession().setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.getSession().setAttribute(ParameterName.PRINTED_DISCOUNTS, printedDiscounts);
        request.getSession().setAttribute(ParameterName.TARIFFS, tariffs);
        return page;
    }
}
