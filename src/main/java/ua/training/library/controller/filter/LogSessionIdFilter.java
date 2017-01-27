package ua.training.library.controller.filter;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author allexb
 * @version 1.0 29.09.2016
 */
@WebFilter(filterName = "logSessionIdFilter")
public class LogSessionIdFilter implements Filter {

    private static final String SESSION_ID = "sessionID";
    private static final String USER = "user";

    private static final Logger logger = Logger.getLogger(LogSessionIdFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if (session != null) {
            MDC.put(SESSION_ID, session.getId());
            User loginedUser = (User) session.getAttribute(Attributes.LOGINED_USER);
            if (loginedUser != null) {
                MDC.put(USER, loginedUser.getLogin());
                logger.info("Loggined user:" + loginedUser.getLogin());
            } else {
                MDC.put(USER, "");
                logger.warn("No user loggined.");
            }
        } else {
            MDC.put(SESSION_ID, "");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
