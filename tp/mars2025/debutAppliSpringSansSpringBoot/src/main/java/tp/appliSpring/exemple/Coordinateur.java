package tp.appliSpring.exemple;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tp.appliSpring.annotation.LogExecutionTime;

@Component //"coordinateur" par defaut
//@Component("monCoordinateur")
public class Coordinateur {
	
    @Autowired //@Qualifier("V2")
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter

	@Autowired @Qualifier("monCalculateurCarre")
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter

	public Coordinateur() {
		System.out.println("constructeur de Coordinateur avec monCalculateur="+monCalculateur);
	}

	@PostConstruct
	public void init() {
		System.out.println("init/@PostConstruct monCalculateur="+monCalculateur);
	}

	@LogExecutionTime
	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
