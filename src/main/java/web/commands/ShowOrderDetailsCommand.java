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



        int order_id = Integer.parseInt(request.getParameter("order_id"));

        //Skal bruge roofid?
        //Order order = of.getOrderByOrderId(order_id);
        //request.setAttribute("order",order);

        return pageToShow;

    }
}
