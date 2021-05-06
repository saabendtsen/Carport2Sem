package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MakeOrderCommand extends CommandProtectedPage {
    private OrderFacade orderFacade;


    public MakeOrderCommand(String pageToShow, String role) {
        super(pageToShow,role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
       double carportLenght = Double.parseDouble(request.getParameter("carportLenght"));
       double carportWidth = Double.parseDouble(request.getParameter("carportWidth"));

        orderFacade.insertIntoCarport(carportLenght, carportWidth);
        return pageToShow;
    }
}
