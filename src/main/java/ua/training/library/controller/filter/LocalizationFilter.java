package ua.training.library.controller.filter;

import ua.training.library.config.LoggingMessages;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.i18n.Languages;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "localizationFilter")
public class LocalizationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LocalizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute(Attributes.LOCALE);
        if (locale == null) {
            locale = Languages.DEFAULT_LOCALE;
            session.setAttribute(Attributes.LOCALE, locale);
            logger.info(LoggingMessages.INFO_DEFAULT_LOCALIZATION + locale.toLanguageTag());
        }
        logger.info("Locale changed. New locale: " + locale.toLanguageTag());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
