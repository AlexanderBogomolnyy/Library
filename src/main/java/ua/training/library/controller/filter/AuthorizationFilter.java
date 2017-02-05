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
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(Attributes.LOGINED_USER);
        if (user == null) {
            logger.warn("Error page. No logged in user");
            httpResponse.sendError(404);
            return;
        }
        if(!isRequestAccessible(httpRequest, user)) {
            logger.warn("Error page. Incorrect status or role.");
            httpResponse.sendError(403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isRequestAccessible(HttpServletRequest request, User user) {
        String requestURI = request.getRequestURI();
        String shortURI = requestURI.replace(Paths.BASE + Paths.HOME, "");
        Objects.requireNonNull(user.getRole());
        boolean isActivated;
        if(shortURI.endsWith(Paths.LOGOUT) || shortURI.endsWith(Paths.PROFILE))
            isActivated = true;
        else {
            isActivated = user.getStatus() == ActivationStatus.ACTIVE;
        }
        boolean isAccessibleDirectory = shortURI.startsWith(user.getRole().name().toLowerCase());
        return  isActivated && isAccessibleDirectory;
    }

    @Override
    public void destroy() {

    }
}
