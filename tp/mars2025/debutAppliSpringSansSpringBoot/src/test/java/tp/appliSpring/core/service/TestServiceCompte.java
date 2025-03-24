package tp.appliSpring.core.service;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.dao.DaoOperation;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {MySpringApplication.class})//reprendre la configuration de la classe principale
//@ActiveProfiles({ "embeddedDB" , "dev" , "perf" })
public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);

	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	//@Autowired
	private DaoOperation daoOperation; //à tester
	
	//@Test
	public void testRechercherCompte() {
		Compte cptA = new Compte(null,"compteA",100.0);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		
        Operation op1 = new Operation(null,"achat 1" , -5.0 , new Date());
        //op1.setCompte(cptA_sauvegarde); daoOperation.save(op1);
		Operation op2 = new Operation(null,"achat 2" , -6.0 , new Date());
		//op2.setCompte(cptA_sauvegarde);daoOperation.save(op2);
		
		Compte cptA_relu = serviceCompte.rechercherCompte(cptA_sauvegarde.getNumero());
		logger.debug("cptA_relu="+cptA_relu);
		/*
		for(Operation op : cptA_relu.getOperations()) {
			logger.debug("\t op="+op);
		}
		*/
		//Assert.assertTrue(.) en JUnit4
		Assertions.assertTrue(cptA_relu.getLabel().equals("compteA"));//en JUnit5"
        //...
	}
	
	//@Test
	public void testVirement() {
		Compte compteASauvegarde = this.serviceCompte.sauvegarderCompte(new Compte(null,"compteA",300.0));
		Compte compteBSauvegarde = this.serviceCompte.sauvegarderCompte(new Compte(null,"compteB",100.0));
		long numCptA = compteASauvegarde.getNumero();
		long numCptB = compteBSauvegarde.getNumero();		
		//remonte en memoire les anciens soldes des compte A et B avant virement (+affichage console ou logger)
		double soldeA_avant= compteASauvegarde.getSolde();
		double soldeB_avant = compteBSauvegarde.getSolde();
		logger.debug("avant bon virement, soldeA_avant="+soldeA_avant + " et soldeB_avant=" + soldeB_avant);
		//effectuer un virement de 50 euros d'un compte A vers vers compte B
		this.serviceCompte.transferer(50.0, numCptA, numCptB);
		//remonte en memoire les nouveaux soldes des compte A et B apres virement (+affichage console ou logger)
	
		Compte compteAReluApresVirement = this.serviceCompte.rechercherCompte(numCptA);
		Compte compteBReluApresVirement = this.serviceCompte.rechercherCompte(numCptB);
		double soldeA_apres = compteAReluApresVirement.getSolde();
		double soldeB_apres = compteBReluApresVirement.getSolde();
		logger.debug("apres bon virement, soldeA_apres="+soldeA_apres + " et soldeB_apres=" + soldeB_apres);
		//verifier -50 et +50 sur les différences de soldes sur A et B
		Assertions.assertEquals(soldeA_avant - 50, soldeA_apres,0.000001);
		Assertions.assertEquals(soldeB_avant + 50, soldeB_apres,0.000001);
	}
	
	//@Test
	public void testMauvaisVirement() {
		/*   VARIANTE A CODER/COLPLETER EN TP 
		COPIER/COLLER à ADPATER de  testVirement()
	            AVEC
		try {
		      this.serviceCompte.transferer(50.0, numCptA, -numCptB); //erreur volontaire
		} catch (Exception e) {
			logger.error("echec normal du virement " + e.getMessage());
		}
		et
		//verifier -0 et +0 sur les différences de soldes sur A et B
		Assertions.assertEquals(soldeA_avant , soldeA_apres,0.000001);
		Assertions.assertEquals(soldeB_avant , soldeB_apres,0.000001);
	            */
	}
}
