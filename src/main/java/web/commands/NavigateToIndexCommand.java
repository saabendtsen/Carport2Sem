package web.commands;

import business.exceptions.UserException;
import business.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NavigateToIndexCommand extends CommandProtectedPage {
    private final MaterialFacade materialFacade;


    public NavigateToIndexCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.materialFacade = new MaterialFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        /// TODO: 06-05-2021 skal laves på et tidspunkt

        request.setAttribute("clothingList", materialFacade.getMaterialByCategoryId(1));
        System.out.println("Setting up clothingList");

        return "index";
    }
}
