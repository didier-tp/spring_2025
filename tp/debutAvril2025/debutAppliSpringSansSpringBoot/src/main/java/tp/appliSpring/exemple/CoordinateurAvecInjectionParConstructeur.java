package tp.appliSpring.exemple;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CoordinateurAvecInjectionParConstructeur {
	
	private MonAfficheur monAfficheur; //référence vers afficheur à injecter
	
	private MonCalculateur monCalculateur;//référence vers calculateur à injecter
	
//@Autowired implicite/automatique si une seule variante du constructeur (sans surcharge)
	public CoordinateurAvecInjectionParConstructeur(
			@Qualifier("V1") MonAfficheur monAfficheur,
			@Qualifier("monCalculateurDouble") MonCalculateur monCalculateur) {
		super();
		this.monAfficheur = monAfficheur;
		this.monCalculateur = monCalculateur;
	}

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
