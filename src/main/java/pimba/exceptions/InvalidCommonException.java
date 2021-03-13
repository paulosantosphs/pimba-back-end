package pimba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulo on 26/04/17.
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "common user not found")
public class InvalidCommonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCommonException(String string) {
        super(string);
    }
}
