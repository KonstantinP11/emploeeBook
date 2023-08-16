package skypro.emploeeBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "сотрудник не найден")
public class EmployeeNotFoundException extends RuntimeException {
}