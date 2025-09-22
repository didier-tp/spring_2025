package tp.sbapp;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SbappApplication {

	@Value("${server.port}")
	private String serverPort;

	@Bean
	public String currrentAppUrl(){
		return "http://localhost:"+ serverPort +"/sbapp";
	}


	public static void main(String[] args) {
		//NB: avec spring.profiles.default=dev_h2,ddl_auto,reinit dans application.properties
		ApplicationContext sbAppContext = SpringApplication.run(SbappApplication.class, args);
		//logger.debug("http://localhost:8181/sbapp");
		log.debug(sbAppContext.getBean("currrentAppUrl",String.class));
	}

}
