package tp.appliSpring.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.exception.BankException;
import tp.appliSpring.bank.core.model.Operation;
import tp.appliSpring.bank.core.service.ServiceCompte;
import tp.appliSpring.bank.core.service.ServiceOperation;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
//@ActiveProfiles({  "dev2" })
public class TestServiceCompte {
    private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);

    @Autowired
    //@Qualifier("direct") // by default, primary
    //@Qualifier("hex")
    private ServiceCompte serviceCompte; //à tester

    @Autowired
    private ServiceOperation serviceOperation;

    @Test
     public void testCompteAvecOperation(){
        Compte cptA = serviceCompte.create(new Compte(null,"compteA",100.0));
        serviceOperation.create(new Operation(null,"achat 1" , -5.0 , new Date()),
                cptA.getNumero());
        serviceOperation.create(new Operation(null,"achat 2" , -6.0 , new Date()),
                cptA.getNumero());
        Compte cptB = serviceCompte.create(new Compte(null,"compteB",200.0));

        Compte compteAReluAvecOp = serviceCompte.searchById(cptA.getNumero());
        assertTrue(compteAReluAvecOp.getLabel().equals("compteA"));
        // afficher les operations de compteAReluAvecOp
        //avec le bon comportement de @Transactional
        List<Operation> operations = serviceOperation.searchByCompte(cptA.getNumero());
        assertTrue(operations.size()==2);
        logger.debug("operations de compteAReluAvecOp="+operations);
     }


    @Test
    public void testVirement() {
        Compte compteASauvegarde = this.serviceCompte.create(
                new Compte(null, "compteA", 300.0));
        Compte compteBSauvegarde = this.serviceCompte.create(
                new Compte(null, "compteB", 100.0));
        long numCptA = compteASauvegarde.getNumero();
        long numCptB = compteBSauvegarde.getNumero();
        //remonter en memoire les anciens soldes des compte A et B avant virement
        //(+affichage console ou logger)
        double soldeA_avant = compteASauvegarde.getSolde();
        double soldeB_avant = compteBSauvegarde.getSolde();
        logger.debug("avant bon virement, soldeA_avant=" + soldeA_avant +
                " et soldeB_avant=" + soldeB_avant);
        //effectuer un virement de 50 euros d'un compte A vers vers compte B
        this.serviceCompte.transfer(50.0, numCptA, numCptB);
        //remonter en memoire les nouveaux soldes des compte A et B apres virement
        // (+affichage console ou logger)
        Compte compteAReluApresVirement =
                this.serviceCompte.searchById(numCptA);
        Compte compteBReluApresVirement =
                this.serviceCompte.searchById(numCptB);
        double soldeA_apres = compteAReluApresVirement.getSolde();
        double soldeB_apres = compteBReluApresVirement.getSolde();
        logger.debug("apres bon virement, soldeA_apres=" + soldeA_apres
                +
                " et soldeB_apres=" + soldeB_apres);
        //verifier -50 et +50 sur les différences de soldes sur A et B :
        Assertions.assertEquals(soldeA_avant - 50, soldeA_apres, 0.000001);
        Assertions.assertEquals(soldeB_avant + 50, soldeB_apres, 0.000001);
    }


    @Test
    public void testMauvaisVirement() {
        Compte compteASauvegarde = this.serviceCompte.create(
                new Compte(null, "compteA", 300.0));
        Compte compteBSauvegarde = this.serviceCompte.create(
                new Compte(null, "compteB", 100.0));
        long numCptA = compteASauvegarde.getNumero();
        long numCptB = compteBSauvegarde.getNumero();
        //remonter en memoire les anciens soldes des compte A et B avant virement
        //(+affichage console ou logger)
        double soldeA_avant = compteASauvegarde.getSolde();
        double soldeB_avant = compteBSauvegarde.getSolde();
        logger.debug("avant mauvais virement, soldeA_avant=" + soldeA_avant +
                " et soldeB_avant=" + soldeB_avant);
        //effectuer un virement de 50 euros d'un compte A vers vers compte B
        try {
            this.serviceCompte.transfer(50.0, numCptA, -numCptB);
        } catch (BankException e) {
            // throw new RuntimeException(e);
            logger.error("erreur normale attendue =, e=" + e.getMessage());
        }
        //remonter en memoire les nouveaux soldes des compte A et B apres virement
        // (+affichage console ou logger)
        Compte compteAReluApresVirement =
                this.serviceCompte.searchById(numCptA);
        Compte compteBReluApresVirement =
                this.serviceCompte.searchById(numCptB);
        double soldeA_apres = compteAReluApresVirement.getSolde();
        double soldeB_apres = compteBReluApresVirement.getSolde();
        logger.debug("apres mauvais virement, soldeA_apres=" + soldeA_apres
                +  " et soldeB_apres=" + soldeB_apres);
        //verifier -0 et +0 sur les différences de soldes sur A et B :
        Assertions.assertEquals(soldeA_avant , soldeA_apres, 0.000001);
        Assertions.assertEquals(soldeB_avant , soldeB_apres, 0.000001);
    }

}
