package tp.sbapp.service;

import java.util.List;
import java.util.Optional;

import tp.sbapp.data.Product;

public interface ProductService {
	List<Product> findAll();
	Optional<Product> findById(Long id);
	Product saveNew(Product p);
	void updateExisting(Product p);
	void deleteById(Long id);
	//...
}
