package tp.mysbapp.service;

import java.util.List;
import java.util.Optional;

import tp.mysbapp.data.Product;

public interface ProductService {
	List<Product> findAll();
	List<Product> findByPrixMini(double prixMini);
	Optional<Product> findById(Long id);
	Product saveNew(Product p);
	void updateExisting(Product p);
	void deleteById(Long id);
	//...
}
