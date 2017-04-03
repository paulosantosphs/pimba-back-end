package pimba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulo on 02/04/17.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "parking not found")
public class InvalidParkException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidParkException(String string) {
        super(string);
    }
}
