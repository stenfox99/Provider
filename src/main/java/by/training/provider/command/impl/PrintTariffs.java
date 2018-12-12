package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.ParameterName;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintTariffs implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter(ParameterName.PAGE_NUMBER));
        List<Tariff> tariffs = new ArrayList<>();
        CommonService service = new CommonService();
        try {
            tariffs = service.findAllTariffs();
        } catch (DaoException e) {
            //TODO EXCEPTION
        }
        List<Tariff> printedTariffs = service.divideListOnPage(tariffs, pageNumber);
        if (tariffs.size() % ParameterName.COUNT_ON_PAGE == 0) {
            request.setAttribute("countPage", tariffs.size() / ParameterName.COUNT_ON_PAGE);
        } else {
            request.setAttribute("countPage", tariffs.size() / ParameterName.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedTariffs", printedTariffs);
        return PagePath.TARIFFS;
    }
}
