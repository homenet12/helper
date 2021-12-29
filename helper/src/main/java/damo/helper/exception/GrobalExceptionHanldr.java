package damo.helper.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GrobalExceptionHanldr {

	@ExceptionHandler(IllegalStateException.class)
	public String stateException(IllegalStateException ex) {
		return ex.getMessage();
	}
}
