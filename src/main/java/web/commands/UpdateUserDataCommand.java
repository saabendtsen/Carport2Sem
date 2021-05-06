package web.commands;

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

        if(newEmail.length() >= 1){
            userFacade.UpdateUserEmail(newEmail,request.getParameter("email"));
        }
        if(newPassword.length() >= 1){
            userFacade.UpdateUserPassword(newPassword,request.getParameter("email"));
        }


        return super.execute(request, response);




    }
}
