package ua.training.library.controller.filter;

import org.apache.log4j.Logger;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "authorizationFilter")
public class AuthorizationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Check authorization.");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Attributes.LOGINED_USER);
        if (user == null) {
            logger.warn("Error page. No logined user");
            httpServletResponse.sendRedirect(Paths.ERROR);
            return;
        }
        String shortURI = requestURI.replace(Paths.BASE + Paths.HOME, "");
        Objects.requireNonNull(user.getRole());
        if (user.getStatus() == ActivationStatus.DEACTIVATED) {
            logger.warn("Access denied.");
            httpServletResponse.sendRedirect(Paths.ACCESS_DENIED);
            return;
        }
        if (!shortURI.startsWith(user.getRole().name().toLowerCase())) {
            logger.warn("Error page. Incorrect role.");
            httpServletResponse.sendRedirect(Paths.ERROR);
            return;
        }
        logger.info("Authorization correctly checked.");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
