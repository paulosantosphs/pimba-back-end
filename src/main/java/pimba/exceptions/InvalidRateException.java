package pimba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulo on 17/05/17.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Rate not found")
public class InvalidRateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidRateException(String message) {
        super(message);
    }
}
