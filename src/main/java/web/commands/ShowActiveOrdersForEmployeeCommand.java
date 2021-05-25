package web.commands;

import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowActiveOrdersForEmployeeCommand extends CommandProtectedPage {
    private final OrderFacade orderFacade;

    public ShowActiveOrdersForEmployeeCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            request.setAttribute("allactiveordersList", orderFacade.getAllOrdersByState());

        } catch (NumberFormatException e) {
            request.setAttribute("error", "");
        }

        return pageToShow;
    }
}
