package tp.mysbapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MysbappApplication {

	@Value("${server.port}")
	private String serverPort;

	@Bean
	public String currrentAppUrl(){
		return "http://localhost:"+ serverPort +"/mysbapp";
	}
	
	private static Logger logger = LoggerFactory.getLogger(MysbappApplication.class);

	public static void main(String[] args) {
		//NB: avec spring.profiles.default=dev_h2,ddl_auto,reinit dans application.properties
		ApplicationContext sbAppContext = SpringApplication.run(MysbappApplication.class, args);
		System.out.println("http://localhost:8181/mysbapp (par defaut)");
		logger.debug(sbAppContext.getBean("currrentAppUrl",String.class));
	}

}
