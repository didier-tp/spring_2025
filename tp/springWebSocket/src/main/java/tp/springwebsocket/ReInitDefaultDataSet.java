package tp.springwebsocket;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.springwebsocket.model.Task;
import tp.springwebsocket.service.ServiceTask;


import java.util.Date;

@Component
//@Profile("reInit")
public class ReInitDefaultDataSet {

	@Autowired
	ServiceTask serviceTask;
	
	@PostConstruct
	public void intialiserJeuxDeDonnees() {
		System.out.println("initialisation d'un jeux de données (en mode developpement)");

		serviceTask.create(new Task(null,"devinette_1" , "quel animal a jamais soif ?"));//"zebu?"
		serviceTask.create(new Task(null,"devinette_2" , "qui est qui est jaune et qui attend ?"));//"Jonathan?"
		serviceTask.create(new Task(null,"devinette_3" , "racine carre de 9 ?"));//"3?"
	}

}
