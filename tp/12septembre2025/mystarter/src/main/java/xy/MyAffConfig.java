package xy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tp.aff.Afficheur;

@Configuration
public class MyAffConfig {
	
	@Value("${tp.message:mm}")  //tp.message=m1 dans application.properties
	private String message;
	
	@Bean
	public Afficheur monAfficheur() {
		return new Afficheur(this.message);
	}

}
