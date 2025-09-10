package tp.sbapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbappApplication {
	
	private static Logger logger = LoggerFactory.getLogger(SbappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SbappApplication.class, args);
		logger.debug("http://localhost:8181/sbapp");
	}

}
