package tp.appliSpring.exemple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleApp {
	public static void main(String[] args) {

		//System.setProperty("spring.profiles.active", "perf");
		System.setProperty("spring.profiles.default", "perf");
		
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfig.class);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//et qui est initialisé selon une ou plusieurs classes de configuration.
		
		//MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		MonCalculateur monCalculateur = contextSpring.getBean("monCalculateurCarre", MonCalculateur.class);
		System.out.println("resCalcul="+monCalculateur.calculer(4));//4*4=16.0 ou autre 

		//Coordinateur coordinateurPrisEnChargeParSpring = contextSpring.getBean(Coordinateur.class);
		//Coordinateur coordinateurPrisEnChargeParSpring = (Coordinateur)contextSpring.getBean("coordinateur");
		Coordinateur coordinateurPrisEnChargeParSpring = contextSpring.getBean("coordinateur", Coordinateur.class);
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();

		CoordinateurAvecInjectionParConstructeur coordinateurAvecInjectionParConstructeurPrisEnChargeParSpring = contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateurAvecInjectionParConstructeurPrisEnChargeParSpring.calculerEtAfficher();
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}
}
