package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.service.OrderService;
import ua.training.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllOrdersCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GetAllClientsCommand.class);

    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO
        User loggedIndUser = getUserFromSession(request);
        try {
            List<Order> orderList = service.getAllAvailable(loggedIndUser.getId(), loggedIndUser.getRole());
            logger.info(LoggingMessages.ORDER_LIST_EXTRACTED);
            request.setAttribute(Attributes.LIST_OF_ORDERS, orderList);
            return Pages.ORDERS_LIST_PAGE;
        } catch (RuntimeException ex) {
            logger.warn(LoggingMessages.ORDER_LIST_ERROR, ex);
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
    }
}
