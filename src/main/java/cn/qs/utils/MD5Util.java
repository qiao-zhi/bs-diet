package cn.qs.utils;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qs.exceptionHandler.MyExceptionHandler;

public class MD5Util {

	private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

	public static String md5(String source, String salt) {
		String des = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (StringUtils.isNotBlank(salt)) {
				source = source + salt;
			}
			byte[] result = md.digest(source.getBytes());
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				byte b = result[i];
				buf.append(String.format("%02X", b));
			}
			des = buf.toString().toLowerCase();
		} catch (Exception e) {
			LOGGER.error("{}", e);
			throw new RuntimeException("md5 failure");
		}
		return des;
	}

	public static void main(String[] args) {
		System.out.println(md5("qlq", "111"));
	}
}
