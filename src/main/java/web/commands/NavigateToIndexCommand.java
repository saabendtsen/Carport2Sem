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
        request.setAttribute("shedClothingList", materialFacade.getMaterialByCategoryId(1));
        request.setAttribute("carportClothingList", materialFacade.getMaterialByCategoryId(2));

        return "index";
    }
}
