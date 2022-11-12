package homework.triple.global;

import homework.triple.global.exception.TravelApplicationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TravelApplicationException.class)
	public CommonResponse handlerBusinessException(final TravelApplicationException e) {
		return CommonResponse.error(e.getErrorCode().name(), e.getMessage());
	}
}
