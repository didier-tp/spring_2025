package tp.appliSpring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tp.appliSpring.entity.Compte;

/*
 CompteRepository est une interface de DAO liée à Spring-Data-Jpa
 et la classe d'implémentation basée sur EntityManager sera générée automatiquement
 */
public interface CompteRepository extends JpaRepository<Compte,Long>{
	
	//méthode respectant convention de nommage findBy...GreaterThanEqual():
	List<Compte> findBySoldeGreaterThanEqual(double soldeMini); //pas de besoin de @Query ici , SELECT généré depuis nom de méthode
	
	//@Query("SELECT c FROM Compte c WHERE c.solde >= ?1")//query par defaut en langage JPQL (JPA Query Langage)
	@Query("SELECT c FROM Compte c WHERE c.solde >= :soldeMini")
	List<Compte> rechercherSelonSoldeMini(@Param("soldeMini") double soldeMini);
	
   /*
    principales méthodes héritées:
    .save()
    .findAll()
    .findById()
    .deleteById()
    ...
    */
}
