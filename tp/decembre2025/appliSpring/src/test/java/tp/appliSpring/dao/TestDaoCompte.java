package tp.appliSpring.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.entity.Compte;

//@SpringBootTest
@SpringBootTest(classes = { AppliSpringApplication.class })
@ActiveProfiles("dev") //pour activer le profile "dev" au démarrage du test
//pour tenir compte du fichier application-dev.properties

//@Slf4j //pour générer l'objet log de slf4j via lombok
public class TestDaoCompte {
	
	private static Logger log = LoggerFactory.getLogger(TestDaoCompte.class);
	
	@Autowired
	private DaoCompte daoCompte; //à tester
	
	@Test
	public void testAjoutEtRelecture() {
		daoCompte.save(new Compte(null,"compteA",50.0));
		daoCompte.save(new Compte(null,"compteB",60.0));
		
		List<Compte> comptes = daoCompte.findAll();
		assertTrue(comptes.size()>=2);
		log.debug("comptes=" + comptes.toString());
	}

}
