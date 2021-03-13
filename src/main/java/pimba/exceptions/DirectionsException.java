package pimba.exceptions;

/**
 * Created by paulo on 28/05/17.
 */
public class DirectionsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DirectionsException(String message) {
        super(message);
    }
}
