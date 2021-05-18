package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.SvgMapper;
import business.services.UserFacade;
import web.FrontController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends CommandUnprotectedPage {
    private final UserFacade userFacade;

    public LoginCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Command command = new NavigateToIndexCommand("index", "customer");
        String getDataAndIndex = command.execute(request, response);

        try {
            User user = userFacade.login(email, password);

            HttpSession session = request.getSession();

            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);

            if(user.getRole().equals("employee")) {
                return "employeepage";
            } else {
                return getDataAndIndex;
            }

        } catch (UserException ex) {
            request.setAttribute("error", "Wrong username or password!");
            return "loginpage";
        }
    }

}
