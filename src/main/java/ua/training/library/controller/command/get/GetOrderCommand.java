package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.RestCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.CatalogService;
import ua.training.library.service.impl.CatalogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetOrderCommand extends RestCommand {

    public static final Logger logger = Logger.getLogger(GetOrderCommand.class);

    private final CatalogService service = CatalogServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException {
        int id = getIdFromRequestURI(request);
        Optional<Catalog> catalog = service.getById(id);
        request.setAttribute(Attributes.CATALOG, catalog.map(cat -> cat).orElse(new Catalog()));

        logger.info("Request for post in order: " + request.getRequestURL());

        request.setAttribute(Attributes.ORDER_PATH, request.getRequestURI());
        if (!catalog.isPresent()) {
            request.setAttribute(Attributes.ERROR_MESSAGE, "This book is unavailable now.");
            return Pages.CATALOG_PAGE;
        }
        return Pages.FILL_ORDER_PAGE;
    }

}
