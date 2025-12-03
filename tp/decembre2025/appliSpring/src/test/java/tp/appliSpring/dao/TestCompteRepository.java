package tp.appliSpring.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.entity.Compte;

//@SpringBootTest
@SpringBootTest(classes = { AppliSpringApplication.class })
@ActiveProfiles("dev") //pour activer le profile "dev" au démarrage du test
//pour tenir compte du fichier application-dev.properties

//@Slf4j //pour générer l'objet log de slf4j via lombok
public class TestCompteRepository {
	
	private static Logger log = LoggerFactory.getLogger(TestCompteRepository.class);
	
	@Autowired
	private CompteRepository daoCompte; //à tester
	
	
	@Test
	public void testAjoutEtRelecture() {
		log.debug("classe du dao : " + daoCompte.getClass().getSimpleName());
		
		daoCompte.save(new Compte(null,"compteA",50.0));
		daoCompte.save(new Compte(null,"compteB",60.0));
		
		List<Compte> comptes = daoCompte.findAll();
		assertTrue(comptes.size()>=2);
		log.debug("comptes=" + comptes.toString());
	}
	
	@Test
	public void testSoldeMini() {
		log.debug("classe du dao : " + daoCompte.getClass().getSimpleName());
		
		daoCompte.save(new Compte(null,"compteA1",50.0));
		daoCompte.save(new Compte(null,"compteB1",60.0));
		daoCompte.save(new Compte(null,"compteC1",70.0));
		daoCompte.save(new Compte(null,"compteD1",20.0));
		
		//List<Compte> comptes = daoCompte.findBySoldeGreaterThanEqual(55.0);
		List<Compte> comptes = daoCompte.rechercherSelonSoldeMini(55.0);
		assertTrue(comptes.size()>=2);
		log.debug("comptes avec solde > 55.0 =" + comptes.toString());
	}
	
	
	/*
	@Test
	@Sql({"/comptes.sql"}) //pour declencher le script comptes.sql placé dans src/test/resources
	public void testAjoutEtRelecture() {
				
		List<Compte> comptes = daoCompte.findAll();
		assertTrue(comptes.size()>=2);
		log.debug("comptes=" + comptes.toString());
	}
     */
	
}
