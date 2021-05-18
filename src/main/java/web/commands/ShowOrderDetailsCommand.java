package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.OrderMapper;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrderDetailsCommand extends CommandProtectedPage{
    OrderFacade of = new OrderFacade(database);


    public ShowOrderDetailsCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {



        int showorderdetails = Integer.parseInt(request.getParameter("showorderdetails"));

        Order order = of.getOrderByOrderId(showorderdetails);
        request.setAttribute("order",order);



        if(request.getParameter("newPrice") != null){
            double newPrice = Double.parseDouble(request.getParameter("newPrice"));
            double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
            double discount = ((oldPrice - newPrice) / oldPrice) * 100;
            request.setAttribute("Discount",discount);
            return pageToShow;
        }


        return pageToShow;

    }
}
