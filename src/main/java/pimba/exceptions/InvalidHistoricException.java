package pimba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulo on 14/05/17.
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Payment must be null if the parking isn't PIMBA")
public class InvalidHistoricException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidHistoricException(String message) {
        super(message);
    }
}
