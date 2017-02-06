package ua.training.library.controller.filter;

import ua.training.library.messages.LoggingMessages;
import ua.training.library.controller.configuration.ResourceNames;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter")
public class EncodingFilter implements Filter {

    private static final Logger logger = Logger.getLogger(EncodingFilter.class);

    public EncodingFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info(LoggingMessages.INFO_ENCODING_FILTER);
        request.setCharacterEncoding(ResourceNames.ENCODING);
        response.setCharacterEncoding(ResourceNames.ENCODING);
        chain.doFilter(request, response);
    }

}
