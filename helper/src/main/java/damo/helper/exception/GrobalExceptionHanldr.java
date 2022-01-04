package damo.helper.exception;

import java.util.NoSuchElementException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GrobalExceptionHanldr {

	@ExceptionHandler(value = {IllegalStateException.class, AccessDeniedException.class})
	public String stateException(IllegalStateException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		return "/error/error";
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public String noSuchException(Model model) {
		model.addAttribute("message", "해당 데이터를 찾을 수 없습니다.");
		return "/error/error";
	}
	
}
