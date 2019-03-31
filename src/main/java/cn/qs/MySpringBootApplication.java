package cn.qs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan("cn") // Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
@EnableScheduling
public class MySpringBootApplication {
	public static void main(String[] args) {
		// 入口运行类
		SpringApplication.run(MySpringBootApplication.class, args);
	}
}
