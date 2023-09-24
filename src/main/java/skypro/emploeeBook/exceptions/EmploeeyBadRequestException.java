package skypro.emploeeBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Недопустимый символ в ФИО")
public class EmploeeyBadRequestException extends RuntimeException {

}
