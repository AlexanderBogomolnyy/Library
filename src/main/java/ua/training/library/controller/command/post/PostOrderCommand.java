package ua.training.library.controller.command.post;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.command.RestCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.Role;
import ua.training.library.model.util.DateHelper;
import ua.training.library.service.OrderService;
import ua.training.library.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostOrderCommand extends RestCommand {

    private static final Logger logger = Logger.getLogger(PostOrderCommand.class);

    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Role role) throws IOException {
        int catalogID = getIdFromRequestURI(request);
        User loginedUser = getUserFromSession(request);
        int userID = loginedUser.getId();

        // TODO add data format util
        String dataOfIssue = request.getParameter("date_of_issue");
        String expectedDataOfReturn = request.getParameter("expected_date_of_return");
        BookLocation expectedBookLocation = BookLocation.valueOf(request.getParameter("expected_book_location"));

        Order order = new Order.Builder()
                .setCatalogID(catalogID)
                .setUserID(userID)
                .setUser(loginedUser)
                .setDateOfIssue(DateHelper.getLocalDateFromString(dataOfIssue))
                .setExpectedDateOfReturn(DateHelper.getLocalDateFromString(expectedDataOfReturn))
                .setExpectedBookLocation(expectedBookLocation)
                .build();

        try {
            service.createNewOrder(order);
            response.sendRedirect(getUrlByRole(loginedUser) + Paths.ORDERS);
            return Pages.REDIRECT;
        } catch (RuntimeException ex) {
            logger.error(LoggingMessages.COMMAND_ERROR_IN_ORDER_CREATING);
            request.setAttribute(Attributes.ERROR_MESSAGE, ex.getMessage());
            return Pages.CATALOG_PAGE;
        }

    }

    private String getUrlByRole(User loginedUser) {
        return Paths.BASE + Paths.DELIMITER + loginedUser.getRole().name().toLowerCase();
    }

    private User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(Attributes.LOGINED_USER);
    }
}
