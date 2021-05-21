package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcDiscountCommand extends CommandUnknown {


    public CalcDiscountCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        double newPrice = Double.parseDouble(request.getParameter("newPrice"));
        double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));

        double discount = ((oldPrice - newPrice) / oldPrice) * 100;

        request.setAttribute("Discount", discount);

        return super.execute(request, response);
    }
}
