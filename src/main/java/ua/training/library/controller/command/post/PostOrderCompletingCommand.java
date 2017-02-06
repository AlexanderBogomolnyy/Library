package ua.training.library.controller.command.post;

import org.apache.log4j.Logger;
import ua.training.library.controller.command.AbstractCommand;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.service.OrderService;
import ua.training.library.service.basic.BasicOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class PostOrderCompletingCommand extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(PostOrderCompletingCommand.class);

    private OrderService service = BasicOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIdFromRequestURI(request);
        try {
            service.updateAndComplete(id, LocalDate.now());
            setSuccessMessageToSession(request, "Order have been completed without library response. Now available examples of the book now.");
        } catch (RuntimeException ex) {
            setErrorMessageToSession(request, ex.getMessage());
        }
        response.sendRedirect(getBaseUrlByRole(request) + Paths.ORDERS);
        return Pages.REDIRECT;
    }

}
