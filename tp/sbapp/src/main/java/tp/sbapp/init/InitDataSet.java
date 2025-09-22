package tp.sbapp.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import tp.sbapp.data.Product;
import tp.sbapp.service.ProductService;



@Component
@Profile("reinit")
public class InitDataSet {
	@Autowired
	ProductService productService;
	
	@PostConstruct
	public void intialiserJeuxDeDonnees() {
		System.out.println("initialisation d'un jeux de données (en mode developpement)");
		productService.saveNew(new Product(null,"styloA",2.2));
		productService.saveNew(new Product(null,"styloB",2.3));
	}

}
