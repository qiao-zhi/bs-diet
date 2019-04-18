package cn.qs.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qs.utils.JSONResultUtil;

//@RestControllerAdvice
public class AjaxExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxExceptionHandler.class);

	// @ExceptionHandler(value = Exception.class)
	public JSONResultUtil defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		LOGGER.error(e.getMessage());
		return JSONResultUtil.error(e.getMessage());
	}
}
