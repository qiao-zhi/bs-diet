package cn.qs.exceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class WebExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

	public static final String ERROR_VIEW = "error";

	// @ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
		LOGGER.error(e.getMessage());
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", reqest.getRequestURL());
		mav.setViewName(ERROR_VIEW);
		return mav;
	}
}
