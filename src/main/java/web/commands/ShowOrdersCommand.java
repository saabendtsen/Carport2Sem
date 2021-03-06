package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrdersCommand extends CommandProtectedPage {
    private final OrderFacade orderFacade;

    public ShowOrdersCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try {
            User user = (User) request.getSession().getAttribute("user");

            request.setAttribute("ordersList", orderFacade.getOrderByUserId(user.getUser_id()));

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Du mangler at udfylde nogle felter!");
        }

        String updateOrder = request.getParameter("updateOrder");
        if (updateOrder != null) {
            int order_id = Integer.parseInt(updateOrder);
            if (orderFacade.updateOrder(order_id) > 0) {
                System.out.println("Ordre: " + order_id + " status er nu opdateret!");
            } else {
                System.out.println("Der skete en fejl under status ├Žndringen");
            }
            Command command = new ShowCustomersForEmployeeCommand("showcustomerorderpage", "employee");
            return command.execute(request, response);
        }

        return pageToShow;
    }
}
