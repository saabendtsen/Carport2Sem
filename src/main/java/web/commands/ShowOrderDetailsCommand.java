package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.SvgMapper;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ShowOrderDetailsCommand extends CommandProtectedPage {
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

            SvgMapper svg = new SvgMapper();

            request.setAttribute("svgdrawing", svg.drawCarport(order));

            String rabat = request.getParameter("rabat");
            if (rabat != null) {
                NumberFormat ft = new DecimalFormat("#0.00");
                double newPrice = Double.parseDouble(request.getParameter("newPrice"));
                request.setAttribute("newPrice", newPrice);

                double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
                double discount = (((oldPrice - newPrice) / oldPrice) * 100);
                request.setAttribute("Discount", ft.format(discount));

                return pageToShow;
            }

            String godkendRabat = request.getParameter("godkendRabat");
            if (godkendRabat != null) {
                double newPrice = Double.parseDouble(request.getParameter("newPrice"));
                if (!order.isOrder_state()) {
                    of.updateOrderSale(order, newPrice);
                    if (of.updateOrder(order.getOrder_id()) > 0) {
                        request.setAttribute("error", "Ordren er leveret til kunden & pris er ændret til " + newPrice + " kr.");
                    } else {
                        request.setAttribute("error", "Ordren kunne ikke leveres");
                    }
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
