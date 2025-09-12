package tp.mysbapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysbappApplication {
	
	private static Logger logger = LoggerFactory.getLogger(MysbappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MysbappApplication.class, args);
		logger.debug("http://localhost:8181/mysbapp");
	}

}
