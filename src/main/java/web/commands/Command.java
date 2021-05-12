package web.commands;

import business.exceptions.UserException;
import business.persistence.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public abstract class Command {
    //Return a token string from the execute method to make a client side redirect,
    // instead of a server side (forward) redirect
    public final static String REDIRECT_INDICATOR = "#*redirect*#_###_";
    public final static String WAS_NOT_FOUND_COMMAND = "404_NOT_FOUND";
    public static Database database;
    private static HashMap<String, Command> commands;

    private static void initCommands(Database database) {
        commands = new HashMap<>();
        commands.put("index", new CommandUnprotectedPage("index"));
        commands.put("loginpage", new CommandUnprotectedPage("loginpage"));
        commands.put("logincommand", new LoginCommand(""));
        commands.put("logoutcommand", new LogoutCommand(""));
        commands.put("registerpage", new CommandUnprotectedPage("registerpage"));
        commands.put("registercommand", new RegisterCommand(""));
        commands.put("customerpage", new CommandProtectedPage("customerpage", "customer"));
        commands.put("employeepage", new CommandProtectedPage("employeepage", "employee"));
        commands.put("updateuserdata", new UpdateUserDataCommand("customerpage","customer"));
        commands.put("orderPage", new MakeOrderCommand("receiptPage", "customer"));
        commands.put("updatestatuscommand", new ShowOrdersCommand("showcustomerorderpage", "employee"));
        commands.put("navigatetoindex", new NavigateToIndexCommand("index", "customer"));
        commands.put("showmyorders", new ShowOrdersCommand("showmyorderspage", "customer"));
        commands.put("showcustomers", new ShowCustomersForEmployeeCommand("showcustomerslisting", "employee"));
        commands.put("showcustomersorders", new ShowCustomersForEmployeeCommand("showcustomerorderpage", "employee"));

        commands.put("showactiveorders", new ShowActiveOrdersForEmployeeCommand("showallactiveorderpage", "employee"));

        commands.put("changeinfo", new CommandProtectedPage("changeuserdata", "customer"));
    }

    public static Command fromPath(HttpServletRequest request, Database db) {
        String action = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + action);

        if (commands == null) {
            database = db;
            initCommands(database);
        }

        return commands.getOrDefault(action, new CommandUnknown());   // unknowncommand is default
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws UserException;
}
