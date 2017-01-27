package ua.training.library.controller.i18n;

import ua.training.library.controller.configuration.Attributes;

import java.util.Arrays;
import java.util.Locale;

public class Languages {

    public static final Locale[] SUPPORTED_LOCALES = {
        new Locale(Attributes.EN, Attributes.EN.toUpperCase()),
        new Locale(Attributes.UA, Attributes.UA.toUpperCase()),
        new Locale(Attributes.RU, Attributes.RU.toUpperCase())
    };

    public static final Locale DEFAULT_LOCALE = new Locale(Attributes.EN, Attributes.EN.toUpperCase());

    public static boolean isSupported(Locale locale) {
        return Arrays.asList(SUPPORTED_LOCALES)
                .stream().filter(x -> x.equals(locale)).findFirst().isPresent();
    }

}
