package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeOrderCommand extends CommandProtectedPage {
    private final OrderFacade orderFacade;


    public MakeOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
            double carportLength = Double.parseDouble(request.getParameter("carportLength"));
            double carportWidth = Double.parseDouble(request.getParameter("carportWidth"));

            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getId();

            orderFacade.createOrder(user_id,carportLength,carportWidth);

        return "index";
    }
}
