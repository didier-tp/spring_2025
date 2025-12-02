package tp.appliSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
//@Slf4j
public class AppliSpringApplication {
	
	private static Logger log = LoggerFactory.getLogger(AppliSpringApplication.class); //générée par @Slf4j

	public static void main(String[] args) { 
		
		String activeProfilesString="dev,eventuel_profil_complementaire_que_jaime";
		//String activeProfilesString="prod,eventuel_profil_complementaire_que_jaime";
		//si le profile "dev" est activé au démarrage de l'application , application-dev.properties est pris en compte
		System.setProperty("spring.profiles.default", activeProfilesString);
		
		SpringApplication.run(AppliSpringApplication.class, args);
		log.debug("http://localhost:8080/appliSpring"); //ou bien log.info("...") ou bien log.error("...")
		//System.out.println("http://localhost:8080 ou http://localhost:8080/appliSpring");
	}

}
