package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.OrderMapper;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ShowOrderDetailsCommand extends CommandProtectedPage{
    OrderFacade of = new OrderFacade(database);


    public ShowOrderDetailsCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {

            int showorderdetails = Integer.parseInt(request.getParameter("showorderdetails"));

            Order order = of.getOrderByOrderId(showorderdetails);
            request.setAttribute("order", order);


            String rabat = request.getParameter("rabat");
            if (rabat != null) {
                double newPrice = Double.parseDouble(request.getParameter("newPrice"));
                request.setAttribute("newPrice", newPrice);
                double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
                NumberFormat formatter = new DecimalFormat("#0.00");
                double discount = (((oldPrice - newPrice) / oldPrice) * 100);

                request.setAttribute("Discount", formatter.format(discount));
                return pageToShow;
            }

            String godkendRabat = request.getParameter("godkendRabat");
            if (godkendRabat != null) {
                double newPrice = Double.parseDouble(request.getParameter("newPrice"));
                if (!order.isOrder_state()){
                  of.updateOrderTotal(order, newPrice);
                  of.updateOrder(order.getOrder_id());
                  order.setOrder_state(true);
                  order.setSaleprice(newPrice);
                }
                return pageToShow;
            }

        } catch (Exception e) {
            request.setAttribute("error", "hov hov");
        }

        return pageToShow;

    }
}
