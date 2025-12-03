package tp.appliSpring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Compte;

/*
 CompteRepository est une interface de DAO liée à Spring-Data-Jpa
 et la classe d'implémentation basée sur EntityManager sera générée automatiquement
 */
public interface CompteRepository extends JpaRepository<Compte,Long>{
	
	//méthode respectant convention de nommage findBy...GreaterThanEqual():
	List<Compte> findBySoldeGreaterThanEqual(double soldeMini); //pas de besoin de @Query ici , SELECT généré depuis nom de méthode
	
   /*
    principales méthodes héritées:
    .save()
    .findAll()
    .findById()
    .deleteById()
    ...
    */
}
