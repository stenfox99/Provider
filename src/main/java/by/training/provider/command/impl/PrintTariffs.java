package by.training.provider.command.impl;

import by.training.provider.command.Command;
import by.training.provider.command.FieldConst;
import by.training.provider.command.PagePath;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.service.WithoutRoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrintTariffs implements Command {
    private static final String PAGE_NUMBER = "pageNumber";

    @Override
    public String execute(HttpServletRequest request) {
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        List<Tariff> tariffs = new ArrayList<>();
        WithoutRoleService service = new WithoutRoleService();
        try {
            tariffs = service.findTariffs();
        } catch (DaoException e) {
            //TODO EXCEPTION
        }
        List<Tariff> printedTariffs = service.getListOnPage(tariffs, pageNumber);
        if (tariffs.size() % FieldConst.COUNT_ON_PAGE == 0){
            request.setAttribute("countPage", tariffs.size() / FieldConst.COUNT_ON_PAGE);
        }else {
            request.setAttribute("countPage", tariffs.size() / FieldConst.COUNT_ON_PAGE + 1);
        }
        request.setAttribute("printedTariffs", printedTariffs);
        return PagePath.printTariffs;
    }
}
