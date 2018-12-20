package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintTariffCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int pageNumber = Integer.parseInt(request.getParameter(ParameterName.PAGE_NUMBER));
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            tariffs = service.findAllTariffs();
            page = PagePath.TARIFFS;
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = PagePath.ERROR;
        }
        List<?> printedTariffs = service.divideListOnPage(tariffs, pageNumber);
        int countPage = service.pageCount(tariffs);
        request.getSession().setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.getSession().setAttribute(ParameterName.PRINTED_TARIFFS, printedTariffs);
        return page;
    }
}
