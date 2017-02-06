package ua.training.library.controller.command.post;

import org.apache.log4j.Logger;
import ua.training.library.messages.LoggingMessages;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.controller.validator.DateStringValidator;
import ua.training.library.controller.validator.EnumValidator;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.util.DateHelper;
import ua.training.library.service.OrderService;
import ua.training.library.service.basic.BasicOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ua.training.library.controller.configuration.Parameters.*;

public class PostOrderCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(PostOrderCommand.class);

    private static final String INVALID_BOOK_LOCATION = "invalid.book_location";
    private static final String INVALID_BOOK_DURATION = "invalid.book.duration";

    private static final int MAXIMUM_DURATION_OF_A_BOOK_ORDER = 22;

    private OrderService service = BasicOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User loggedInUser = getUserFromSession(request);
        List<String> errors = new ArrayList<>();
        Order order = getCheckedOrderFromRequestCompletedByUser(request, loggedInUser, errors);
        if (errors.isEmpty()) {
            try {
                service.createNewOrder(order, loggedInUser.getRole());
                response.sendRedirect(getBaseUrlByRole(request) + Paths.ORDERS);
                return Pages.REDIRECT;
            } catch (RuntimeException ex) {
                logger.error(LoggingMessages.COMMAND_ERROR_IN_ORDER_CREATING);
                errors.add(ex.getMessage());
            }
        }
        setErrorMessageToSession(request, Arrays.toString(errors.toArray()));
        response.sendRedirect(request.getRequestURI());
        return Pages.REDIRECT;
    }

    private Order getCheckedOrderFromRequestCompletedByUser(HttpServletRequest request, User loggedInUser, List<String> errors) {
        Order order = parseRequestData(request, errors);
        if(order.getExpectedBookLocation() == BookLocation.READING_ROOM)
            order.setExpectedDateOfReturn(LocalDate.now());
        order.setUser(loggedInUser);
        order.setUserId(loggedInUser.getId());
        return order;
    }

    private Order parseRequestData(HttpServletRequest request, List<String> errors) {
        return new Order.Builder()
                .setCatalogID(getIdFromRequestURI(request))
                .setDateOfIssue(getValidDate(request.getParameter(DATE_OF_ISSUE), 0, errors))
                .setExpectedDateOfReturn(getValidDate(request.getParameter(EXPECTED_DATE_OF_RETURN), MAXIMUM_DURATION_OF_A_BOOK_ORDER, errors))
                .setExpectedBookLocation(getValidBookLocationEnum(request.getParameter(EXPECTED_BOOK_LOCATION), errors))
                .build();
    }

    private BookLocation getValidBookLocationEnum(String parameter, List<String> errors) {
        EnumValidator validator = new EnumValidator(BookLocation.class, INVALID_BOOK_LOCATION);
        if (validator.validate(parameter)) {
            BookLocation bookLocation = BookLocation.valueOf(parameter);
            if (bookLocation == BookLocation.LIBRARY)
                errors.add(INVALID_BOOK_LOCATION);
            return bookLocation;
        }else {
            errors.add(validator.getErrorMessage());
            return BookLocation.ON_HAND;
        }
    }

    private LocalDate getValidDate(String parameter, int duration, List<String> errors) {
        DateStringValidator validator = new DateStringValidator();
        LocalDate extractedDate;
        if (validator.validate(parameter))
            extractedDate = DateHelper.getLocalDateFromString(parameter);
        else {
            errors.add(validator.getErrorMessage());
            extractedDate = LocalDate.now().minusDays(1);
        }
        LocalDate maxDate = LocalDate.now().plusDays(duration);
        if (extractedDate.isAfter(maxDate) || extractedDate.isBefore(LocalDate.now()))
            errors.add(INVALID_BOOK_DURATION);
        return extractedDate;
    }

}
