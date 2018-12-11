package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.service.WithoutRoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintDiscounts implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter(FieldConst.PAGE_NUMBER));
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
        if (pageNumber * FieldConst.COUNT_ON_PAGE + FieldConst.COUNT_ON_PAGE >= discounts.size()) {
            printedDiscounts = discounts.subList(pageNumber * FieldConst.COUNT_ON_PAGE, discounts.size());
        } else {
            printedDiscounts = discounts.subList(pageNumber * FieldConst.COUNT_ON_PAGE, pageNumber
                    * FieldConst.COUNT_ON_PAGE + FieldConst.COUNT_ON_PAGE);
        }
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
