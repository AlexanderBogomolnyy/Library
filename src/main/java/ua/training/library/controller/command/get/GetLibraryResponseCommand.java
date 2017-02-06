package ua.training.library.controller.command.get;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.Order;
import ua.training.library.service.OrderService;
import ua.training.library.service.basic.BasicOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GetLibraryResponseCommand extends AbstractCommand {

    public static final Logger logger = Logger.getLogger(GetLibraryResponseCommand.class);

    private OrderService service = BasicOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIdFromRequestURI(request);
        try {
            Optional<Order> order = service.getById(id);
            Order selectedOrder = order.orElseGet(Order::new);
            request.setAttribute(Attributes.RESPONSE_ORDER, selectedOrder);
            request.setAttribute(Attributes.RESPONSE_PATH, request.getRequestURI());
            request.setAttribute(Attributes.ORDER_COMPLETING_PATH,
                    getBaseUrlByRole(request) + Paths.ORDERS + Paths.DELIMITER + id + Paths.COMPLETING);
            if (!order.isPresent()) {
                setErrorMessageToSession(request, "This order already performed or have not been created.");
                response.sendRedirect(getBaseUrlByRole(request) + Paths.ORDERS);
                return Pages.REDIRECT;
            }
            List<Book> bookList = service.getBooksForOrder(selectedOrder);
            request.setAttribute(Attributes.EXAMPLES_OF_A_BOOK, bookList);
            return Pages.LIBRARY_RESPONSE_FORM_PAGE;
        } catch (RuntimeException ex) {
            setErrorMessageToSession(request, ex.getMessage());
            response.sendError(500);
            return Pages.REDIRECT;
        }
    }

}
