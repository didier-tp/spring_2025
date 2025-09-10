package tp.sbapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.sbapp.data.Product;
import tp.sbapp.service.ProductService;


@RestController
@RequestMapping(value="/rest/api-product/products" , headers="Accept=application/json")
public class ProductRestCtrl {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long idProd) {
			return ResponseEntity.of(productService.findById(idProd));
	}
	
	@GetMapping()
	public List<Product> getProductsByCriteria(
			/*@RequestParam(value="paramName",required=false) Double param*/
			){
		return productService.findAll();
	}

}
