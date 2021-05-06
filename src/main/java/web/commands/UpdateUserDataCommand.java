package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserDataCommand extends CommandProtectedPage{
    private final UserFacade userFacade;

    public UpdateUserDataCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        User user = (User)request.getSession().getAttribute("user");

        //TODO: Check om bruger allerede eksistere.
        if(newEmail.length() >= 1){

            //Check if no rows has been affeted
            if(userFacade.UpdateUserEmail(newEmail,user) < 1){
            request.setAttribute("error", "Something went wrong");
            }
        }
        if(newPassword.length() >= 1){
            //Check if no rows has been affeted
            if(userFacade.UpdateUserPassword(newPassword,user) < 1){

            }
        }


        return super.execute(request, response);




    }
}
