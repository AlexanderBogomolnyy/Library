package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCatalogCommand implements Command{

    private static final Logger logger = Logger.getLogger(GetCatalogCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
