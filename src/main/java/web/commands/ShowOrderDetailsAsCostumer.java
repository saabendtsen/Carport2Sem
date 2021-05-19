package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.SvgMapper;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrderDetailsAsCostumer extends CommandProtectedPage {
    OrderFacade of = new OrderFacade(database);

    public ShowOrderDetailsAsCostumer(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        int showorderdetails = Integer.parseInt(request.getParameter("showorderdetails"));
        Order order = of.getOrderByOrderId(showorderdetails);
        request.setAttribute("order", order);

        SvgMapper svg = new SvgMapper();
        String drawing = svg.drawCarport(order);
        request.setAttribute("svgdrawing", drawing);


        return pageToShow;
    }
}
