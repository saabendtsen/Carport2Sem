package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand extends CommandUnprotectedPage {
    private final UserFacade userFacade;

    public RegisterCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        try {

            if (password1.equals(password2)) {
                User user = userFacade.createUser(email, password1);
                HttpSession session = request.getSession();

                session.setAttribute("email", email);
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                return user.getRole() + "page";
            } else {
                request.setAttribute("error", "De to koder passer ikke sammen");
                return "registerpage";
            }

        } catch (UserException e){
            request.setAttribute("error", "Bruger med Email: "+ email +" eksistere allerede!");
            return "registerpage";
        }

    }

}
