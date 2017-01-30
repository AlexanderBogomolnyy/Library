package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.BookStatus;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.CatalogService;
import ua.training.library.service.impl.CatalogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetCatalogCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetCatalogCommand.class);

    CatalogService service = CatalogServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException {
        try {
            List<Catalog> catalog = service.getAllActive();
            logger.info(LoggingMessages.CATALOG_EXTRACTED);
            request.setAttribute(Attributes.CATALOG, catalog);
            return Pages.CATALOG_PAGE;
        } catch (Exception ex) {
            logger.warn(LoggingMessages.CATALOG_ERROR + " " + ex.getMessage(), ex);
//            request.setAttribute("javax.servlet.error.exception", ex);
//            request.setAttribute("javax.servlet.error.status_code", 500);
//            response.setStatus(500);

//            response.sendError(404);

//            try {
            // TODO убрать try/catch и перенести в контроллер
                response.sendRedirect(Paths.ERROR);
//            } catch (IOException e) {
//                logger.error(LoggingMessages.CONTROLLER_ERROR_IN_REDIRECTION);
//            }
            return Pages.REDIRECT;
        }
    }
}
