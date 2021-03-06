package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.MaterialMapper;
import business.persistence.SvgMapper;
import business.services.MaterialFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeOrderCommand extends CommandProtectedPage {
    private final OrderFacade orderFacade;
    private final MaterialFacade materialFacade;

    public MakeOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
        this.materialFacade = new MaterialFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            double carportLength = Double.parseDouble(request.getParameter("carportLength"));
            double carportWidth = Double.parseDouble(request.getParameter("carportWidth"));
            double shedLength = Double.parseDouble(request.getParameter("shedLength"));
            double shedWidth = Double.parseDouble(request.getParameter("shedWidth"));
            int carportRoof_materialID = Integer.parseInt(request.getParameter("carportRoof"));
            int shedClothing_materialID = Integer.parseInt(request.getParameter("shedClothing"));

            double totalLength = (carportLength-45) - shedLength;
            double totalwidth = (carportWidth*0.9) - shedWidth;

            if (totalLength < 30 || totalwidth < 30) {
                request.setAttribute("error", "Redskabsrums er større end carporten, vælg en mindre størrelse");
                Command command = new NavigateToIndexCommand("index", "customer");
                return command.execute(request, response);
            }

            if (shedLength > 0 && shedWidth == 0 && shedClothing_materialID == 0 || shedLength == 0 && shedWidth > 0 && shedClothing_materialID == 0) {
                request.setAttribute("error", "Du mangler at udflyde et felt");
                Command command = new NavigateToIndexCommand("index", "customer");
                return command.execute(request, response);
            }

            User user = (User) request.getSession().getAttribute("user");
            int user_id = user.getUser_id();
            int orderid = orderFacade.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth);
            Order order = orderFacade.getOrderByOrderId(orderid, carportRoof_materialID);
            order.getShed().setClothing(materialFacade.getMaterialByMaterialId(shedClothing_materialID));

            materialFacade.calcMaterialList(order);

            request.setAttribute("order", order);

            SvgMapper svgMapper = new SvgMapper();
            String svg = svgMapper.drawCarport(order);
            request.setAttribute("svgdrawing", svg);

        } catch (Exception e) {
            request.setAttribute("error", "Du mangler at udfylde nogle felter!");

            Command command = new NavigateToIndexCommand("index", "customer");
            return command.execute(request, response);
        }

        return pageToShow;
    }
}
