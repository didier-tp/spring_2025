package tp.mysbapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//NB: @SpringBootApplication est un équivalent
// de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package
@SpringBootApplication
public class MysbappApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MysbappApplication.class)
		 .profiles("dev_h2,ddl_auto");  //setting profiles here

	}

	private static Logger logger = LoggerFactory.getLogger(MysbappApplication.class);

	public static void main(String[] args) {

		String profilesActifs = System.getProperty("spring.profiles.active");
		logger.info("profilesActifs=" + profilesActifs);
		SpringApplication.run(MysbappApplication.class, args);
		//logger.info("http://localhost:8181/mysbapp");
		logger.debug("http://localhost:8181/mysbapp");

	}


}
