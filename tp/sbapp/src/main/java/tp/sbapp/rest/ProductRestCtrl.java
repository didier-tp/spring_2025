package tp.sbapp.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tp.sbapp.data.Product;
import tp.sbapp.data.ProductToCreate;
import tp.sbapp.service.ProductService;


@RestController
@RequestMapping(value="/rest/api-product/products" , headers="Accept=application/json")
public class ProductRestCtrl {
	
	@Autowired
	private ProductService productService;

	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: http://localhost:8181/sbapp/rest/api-product/products/1
	@GetMapping("/{id}" )
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long idProduct) {
		Optional<Product> productOptional = productService.findById(idProduct)  ;
		return ResponseEntity.of(productOptional); //retourne ok + données si pas vide ou bien NOT_FOUND/404 si vide
	}

	//RECHERCHE MULTIPLE :
	//URL de déclenchement: http://localhost:8181/sbapp/rest/api-product/products
	//                   ou http://localhost:8181/sbapp/rest/api-product/products?prixMini=2
	@GetMapping()
	public List<Product> getProductsByCriteria(
			@RequestParam(value="prixMini",required=false) Double prixMini) {
		if(prixMini==null)
			return productService.findAll();
		else
			return productService.findByPrixMini(prixMini); //nouvelle méthode à coder et appeler
	}

	//appelé en mode POST
	//avec url = http://localhost:8181/sbapp/rest/api-product/products
	//avec dans la partie "body" de la requête { "id" : null , "label" : "…." , "price" : 50.0 }
	@PostMapping("")
	public ResponseEntity<Product> postProduct(/*@Valid*/ @RequestBody ProductToCreate obj) {
		Product savedObj = productService.saveNew(obj); //avec id auto_incrémenté
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedObj .getId()).toUri();
		//return ResponseEntity.created(location).build();
		//return 201/CREATED , no body but URI to find added obj
		return ResponseEntity.created(location).body(savedObj);
		//return 201/CREATED with savedObj AND with URI to find added obj
        /* ou bien encore return ResponseEntity.ok()
        .headers(responseHeadersWithLocation).body(savedObj);
        */
	}

}
