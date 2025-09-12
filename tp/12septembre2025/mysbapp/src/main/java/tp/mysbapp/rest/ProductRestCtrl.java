package tp.mysbapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.mysbapp.data.Product;
import tp.mysbapp.service.ProductService;

@RestController
@RequestMapping(value="/rest/api-product/v1/products" , headers="Accept=application/json")
public class ProductRestCtrl {
	
	@Autowired
	private ProductService productService;

	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: .../mysbapp/api-producti/v1/products/1
	@GetMapping("/{id}" )
	public Product getDeviseByCode(@PathVariable("id") Long id) {
	return productService.findById(id).get();
	}
	//RECHERCHE MULTIPLE :
	//URL de déclenchement: /mysbapp/api-product/v1/products 
	//ou                    /mysbapp/api-product/v1/products?priceMaxi=5
	@GetMapping()
	public List<Product> getProductsByCriteria(
	@RequestParam(value="priceMaxi",required=false) Double priceMaxi) {
	if(priceMaxi==null)
	   return productService.findAll();
	else 
		return productService.findAll();
	    //return productService.findProductByMaxPrice(priceMaxi);
	}
}
