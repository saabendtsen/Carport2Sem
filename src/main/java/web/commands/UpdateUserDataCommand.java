package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserDataCommand extends CommandProtectedPage {
    private final UserFacade userFacade;

    public UpdateUserDataCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        User user = (User) request.getSession().getAttribute("user");

        //TODO: Viser stadig gammelt username indtil logge ud

        if (newEmail.length() >= 1) {
            if (!userFacade.CheckUserEmail(newEmail)) {
                userFacade.UpdateUserEmail(newEmail, user);
                user.setEmail(newEmail);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            } else {
                request.setAttribute("error", "User already exist");
            }
        }

        if (newPassword.length() >= 1) {
            if (userFacade.UpdateUserPassword(newPassword, user) < 1) {
            }
        }

        return super.execute(request, response);
    }
}
