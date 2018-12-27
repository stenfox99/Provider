package by.training.provider.command.impl.tariff;

import by.training.provider.command.*;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.LogicException;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintTariffCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router page;
        int pageNumber = Integer.parseInt(request.getParameter(ParameterName.PAGE_NUMBER));
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            tariffs = service.findAllTariffs();
            page = new Router(PagePath.TARIFFS, DirectionType.FORWARD);
        } catch (LogicException e) {
            request.setAttribute(ParameterName.ERROR, e);
            page = new Router(PagePath.ERROR, DirectionType.FORWARD);
        }
        List<?> printedTariffs = service.divideListOnPage(tariffs, pageNumber);
        int countPage = service.pageCount(tariffs);
        request.getSession().setAttribute(ParameterName.COUNT_PAGE, countPage);
        request.getSession().setAttribute(ParameterName.PRINTED_TARIFFS, printedTariffs);
        return page;
    }
}
