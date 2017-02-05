package ua.training.library.controller.validator;


/**
 * Interface for validator.
 * Validator interface provides methods for performing validation of data and
 * getting error message in case if input value is not valid.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 29.01.2017.
 */
public interface Validator<T> {
    /**
     * The method returns error message after validation or empty value
     * if validation performed well
     *
     * @return error message
     */
    String getErrorMessage();

    /**
     * Check is input object is valid
     *
     * @param object - validation object
     * @return {@code true} if valid
     *         {@code false} if invalid
     */
    boolean validate(T object);
}
