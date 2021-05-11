package web.commands;

import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.MaterialFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MakeOrderCommand extends CommandProtectedPage {
    private final OrderFacade orderFacade;
    private MaterialFacade materialFacade;

    public MakeOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
        this.materialFacade = new MaterialFacade(database);
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


            // TODO: 11-05-2021 mangler at bruge dem, de henter material_id btw!!
            int carportRoof = Integer.parseInt(request.getParameter("carportRoof"));
            request.setAttribute("carportRoof",carportRoof);

            int shedClothing = Integer.parseInt(request.getParameter("shedClothing"));
            request.setAttribute("shedClothing",shedClothing);


            double totalLength = carportLength-shedLength;
            double totalwidth = carportWidth-shedWidth;

            if (totalLength < 30 || totalwidth < 30 ) {
                request.setAttribute("error", "Redskabsrums er større end carporten, vælg en mindre størrelse");

                Command command = new NavigateToIndexCommand("index", "customer");
                return command.execute(request,response);
            }


            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getUser_id();

            int orderid = orderFacade.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth);


            List<Material> stkList = materialFacade.calcMaterialList(orderFacade.getOrderByOrderId(orderid));
            request.setAttribute("stkList",stkList);


        } catch (NumberFormatException e) {
            request.setAttribute("error", "Du mangler at udfylde nogle felter!");

            Command command = new NavigateToIndexCommand("index", "customer");
            return command.execute(request,response);
        }





        return pageToShow;

    }
}
