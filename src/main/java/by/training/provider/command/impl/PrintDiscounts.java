package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintDiscounts implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter(ParameterName.PAGE_NUMBER));
        List<Discount> discounts = new ArrayList<>();
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            discounts = service.findAllDiscounts();
            tariffs = service.findAllTariffs();
        } catch (DaoException e) {
            //TODO EXCEPTION
        }
        List<Discount> printedDiscounts = service.divideListOnPage(discounts, pageNumber);
        int countPage = service.pageCount(discounts);
        request.setAttribute("countPage", countPage);
        request.setAttribute("printedDiscounts", printedDiscounts);
        request.setAttribute("TARIFFS", tariffs);
        return PagePath.DISCOUNTS;
    }
}
