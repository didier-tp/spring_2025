package tp.mysbapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp.mysbapp.entity.ProductEntity;

import java.util.List;

/*
interface abstraite d'une classe de traitement d'accès au données
DAO: Data Access Object (alias repository : jargon Spring)
avec méthodes CRUD
 */

public interface ProductDao extends JpaRepository<ProductEntity, Long> {
    /* principales méthodes héritées:
    .save()
    .findById()
    .deleteById()
     */

    //méthode de recherche respectant convention de nom de méthode ==> sql généré automatiquement
    List<ProductEntity> findByPriceLessThanEqual(double prixMaxi);

    @Query("SELECT p FROM ProductEntity p WHERE p.price >= ?1 ") //syntaxe JPA-QL (SQL adapté à java)
    List<ProductEntity> findByMinimumPrice(double prixMini);
}
