package skypro.emploeeBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "превышен лимит количества сотрудников в фирме")
public class EmployeeStorageIsFullException extends RuntimeException {
}
