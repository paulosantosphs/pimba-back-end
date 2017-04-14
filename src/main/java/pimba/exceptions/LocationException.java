package pimba.exceptions;

/**
 * Created by paulo on 14/04/17.
 */
public class LocationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LocationException() {
        super();
    }

    public LocationException(String message) {
        super(message);
    }

}
