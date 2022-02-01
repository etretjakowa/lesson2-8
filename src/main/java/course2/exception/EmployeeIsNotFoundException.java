package course2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeIsNotFoundException extends RuntimeException {
    public EmployeeIsNotFoundException() {

    }
    public EmployeeIsNotFoundException(String errorMessage) {
        super(errorMessage);
    }


}
