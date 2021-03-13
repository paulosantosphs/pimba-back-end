package pimba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulo on 13/05/17.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Stars must be between 0 and 5")
public class InvalidEvaluationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidEvaluationException(String message) {
        super(message);
    }
}
