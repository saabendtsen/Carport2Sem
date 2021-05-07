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
            User user = (User)request.getSession().getAttribute("user");

            request.setAttribute("ordersList", orderFacade.getOrderByUserId(user.getId()));

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Du mangler at udfylde nogle felter!");
        }


        return pageToShow;
    }
}
