package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.OrderService;
import ua.training.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAllOrdersCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetAllClientsCommand.class);

    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException {
        User loginedUser = getUserFromSession(request);
        try {
            List<Order> orderList = service.getAllAvailable(loginedUser.getId(), role);
            logger.info(LoggingMessages.ORDER_LIST_EXTRACTED);
            request.setAttribute(Attributes.LIST_OF_ORDERS, orderList);
            return Pages.ORDERS_PAGE;
        } catch (RuntimeException ex) {
            logger.warn(LoggingMessages.ORDER_LIST_ERROR, ex);
            response.sendRedirect(Paths.ERROR);
            return Pages.REDIRECT;
        }
    }

    private User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(Attributes.LOGINED_USER);
    }
}
