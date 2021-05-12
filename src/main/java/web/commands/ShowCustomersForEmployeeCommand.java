package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCustomersForEmployeeCommand extends CommandProtectedPage {
    private final OrderFacade orderFacade;
    private final UserFacade userFacade;

    public ShowCustomersForEmployeeCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
        this.userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            // Lav en user liste af vores users
            request.setAttribute("userlist", userFacade.getAllUsers());

            String chosen_userid = request.getParameter("chosen_userid");
            if (chosen_userid != null) {
                // tage den valgte user_id fra sessionscopet (den valgte user id) og gemme den til senere brug
                int user_id = Integer.parseInt(chosen_userid);
                // det valgte user id bruger vi nu til at grave ordre frem på og vise dem
                request.setAttribute("specificuserList", orderFacade.getOrderByUserId(user_id));

                return "showcustomerorderpage";
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Du mangler at vælge noget!");
        }

        return pageToShow;
    }
}
