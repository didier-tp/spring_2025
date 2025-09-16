package tp.mysbapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.mysbapp.data.Product;
import tp.mysbapp.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="rest/api-product/products" , headers="Accept=application/json")
public class ProductRestCtrl {

    @Autowired
    private ProductService productService;

    //RECHERCHE UNIQUE selon RESOURCE-ID:
    //URL de déclenchement: http://localhost:8181/mysbapp/rest/api-product/products/1
    @GetMapping("/{id}" )
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long idProduct) {
        Optional<Product> productOptional = productService.findById(idProduct)  ;
        return ResponseEntity.of(productOptional); //retourne ok + données si pas vide ou bien NOT8FOUND/404 si vide
    }

    //RECHERCHE MULTIPLE :
    //URL de déclenchement: http://localhost:8181/mysbapp/rest/api-product/products
    //                   ou http://localhost:8181/mysbapp/rest/api-product/products?prixMini=2
    @GetMapping()
    public List<Product> getProductsByCriteria(
            @RequestParam(value="prixMini",required=false) Double prixMini) {
        if(prixMini==null)
            return productService.findAll();
        else
            //return productService.findAll();
            return productService.findByPrixMini(prixMini); //nouvelle méthode à coder et appeler
    }

}
