package pimba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paulo on 28/04/17.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "vehicle not found")
public class InvalidVehicleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidVehicleException(String string) {
        super();
    }
}
