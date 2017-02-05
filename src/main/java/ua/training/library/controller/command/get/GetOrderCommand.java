package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.Catalog;
import ua.training.library.service.CatalogService;
import ua.training.library.service.impl.CatalogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetOrderCommand extends AbstractCommand {

    public static final Logger logger = Logger.getLogger(GetOrderCommand.class);

    private final CatalogService service = CatalogServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIdFromRequestURI(request);
        try {
            Optional<Catalog> catalog = service.getById(id);
            request.setAttribute(Attributes.ORDER_CATALOG, catalog.orElseGet(Catalog::new));

            logger.info("Request for post in order: " + request.getRequestURL());

            request.setAttribute(Attributes.ORDER_PATH, request.getRequestURI());
            if (!catalog.isPresent()) {
                request.getSession().setAttribute(Attributes.ERROR_MESSAGE, "This book is unavailable now.");
                response.sendRedirect(getBaseUrlByRole(request) + Paths.CATALOG);
                return Pages.REDIRECT;
            }
        } catch (RuntimeException ex) {
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
        return Pages.ORDER_FORM_PAGE;
    }

}
