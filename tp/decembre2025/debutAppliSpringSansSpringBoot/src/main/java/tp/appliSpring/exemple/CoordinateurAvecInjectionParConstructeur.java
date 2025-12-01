package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor()
public class CoordinateurAvecInjectionParConstructeur {
	
	
	private final MonAfficheur monAfficheur; //référence vers afficheur à injecter
	
	private final MonCalculateur monCalculateur;//référence vers calculateur à injecter
	
	
   /*
	//@Autowired implicit ici car un seul constructeur dans cette classe
	//injection par constructeur
	//@Autowired
	public CoordinateurAvecInjectionParConstructeur(
			MonAfficheur monAfficheur, 
			MonCalculateur monCalculateur) {
		super();
		this.monAfficheur = monAfficheur;
		this.monCalculateur = monCalculateur;
	}
	*/

	public void calculerEtAfficher() {
		double x=4;
		double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
}
