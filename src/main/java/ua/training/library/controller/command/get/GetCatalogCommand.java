package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.model.entity.Catalog;
import ua.training.library.service.CatalogService;
import ua.training.library.service.impl.CatalogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCatalogCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GetCatalogCommand.class);

    CatalogService service = CatalogServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Catalog> catalog = service.getAllActive();
            logger.info(LoggingMessages.CATALOG_EXTRACTED);
            request.setAttribute(Attributes.CATALOG_LIST, catalog);
            return Pages.CATALOG_LIST_PAGE;
        } catch (RuntimeException ex) {
            logger.warn(LoggingMessages.CATALOG_ERROR + " " + ex.getMessage(), ex);
            // TODO убрать try/catch и перенести в контроллер
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
    }
}
