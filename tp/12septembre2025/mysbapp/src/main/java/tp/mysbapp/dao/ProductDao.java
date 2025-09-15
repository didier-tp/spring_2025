package tp.mysbapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tp.mysbapp.entity.ProductEntity;

public interface ProductDao extends JpaRepository<ProductEntity,Long>{
    //méthodes héritées : .save() , .findAll() , .findById() , ...
	
	//NB: la methode de recherche findByPriceLessThanEqual() respecte une convention de nommage
	//le code sql sera généré automatiquement
	List<ProductEntity> findByPriceLessThanEqual(Double prixMaxi);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.price <= :prixMaxi ")
	List<ProductEntity> findByPriceMaxi(Double prixMaxi);
}
