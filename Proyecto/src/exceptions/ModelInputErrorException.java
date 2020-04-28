package exceptions;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ModelInputErrorException extends RuntimeException {

    public ModelInputErrorException() {
    }

    public ModelInputErrorException(String message) {
        super(message);
    }

}
