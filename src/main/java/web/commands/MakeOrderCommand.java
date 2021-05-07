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
        try {
            double carportLength = Double.parseDouble(request.getParameter("carportLength"));
            request.setAttribute("carportLength",carportLength);

            double carportWidth = Double.parseDouble(request.getParameter("carportWidth"));
            request.setAttribute("carportWidth",carportWidth);

            double shedLength = Double.parseDouble(request.getParameter("shedLength"));
            request.setAttribute("shedLength",shedLength);

            double shedWidth = Double.parseDouble(request.getParameter("shedWidth"));
            request.setAttribute("shedWidth",shedWidth);


            double totalLength = carportLength-shedLength;
            double totalwidth = carportWidth-shedWidth;

            if (totalLength < 30 || totalwidth < 30 ) {
                request.setAttribute("error", "Redskabsrums er større end carporten, vælg en mindre størrelse");
                return "index";
            }


            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getUser_id();

            orderFacade.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Du mangler at udfylde nogle felter!");
            Command command = new NavigateToIndexCommand("index", "customer");
            return command.execute(request,response);
        }


        return pageToShow;

    }
}
