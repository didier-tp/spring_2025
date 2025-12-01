package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Coordinateur {
	
	@Autowired @Qualifier("v2") //ou "v1"
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	@Autowired
	private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
	
	public Coordinateur(){
		System.out.println(">> dans constructeur de Coordinateur , monAfficheur="+monAfficheur);
	}
	
	@PostConstruct
	public void initialiser() {
		System.out.println(">> dans initialiser() préfixé par @PostConstruct , monAfficheur="+monAfficheur);
	}

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
