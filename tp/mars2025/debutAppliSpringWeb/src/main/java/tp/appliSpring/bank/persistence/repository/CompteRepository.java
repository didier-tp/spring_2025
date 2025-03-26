package tp.appliSpring.bank.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.persistence.entity.CompteEntity;

/*
 NB: avec l'extension spring-data , une classe d'implémentation de cette interface
     est générée automatiquement et c'est injectable avec @Autowired
 */

public interface CompteRepository extends JpaRepository<CompteEntity,Long>{
    /*
     par héritage , on a :
	.save()
	.findById()
	.findAll()
	.deleteById()
	....
	*/

	//NB: le code de la requête déclenchée sera généré automatiquement
	//par spring-Data via des conventions de nom de méthode (même pas besoin de @NamedQuery)
	//findBy+Clients+Numero où clients vient du fait qu'il existe .clients dans classe Compte
	// et numero vient du fait qu'il existe .numero dans la classe Client
	List<CompteEntity> findByClientsNumero(Long numCli);
	
	//code de la requete dans @NamedQuery("CompteEntity.findWithOperations")
	@Query("SELECT cpt FROM CompteEntity cpt LEFT JOIN FETCH cpt.operations WHERE cpt.numero = ?1")
	CompteEntity findWithOperations(long numCompte);
	
	//le code/la requete de cette méthode va être généré automatiquement (sans @NamedQuery)
	//car on a respecter la convention de nom de methode
	//findBy+Solde+GreaterThanEqual avec Compte.solde qui existe
	List<CompteEntity> findBySoldeGreaterThanEqual(double soldeMini);

	//PROJECTION JPA/HIBERNATE (SELECT NEW) directe vers DTO sur requête
	@Query("SELECT new tp.appliSpring.bank.core.model.Compte(c.numero,c.label,c.solde) FROM CompteEntity c WHERE c.solde >= ?1")
	List<Compte> findAsCompteBySoldeGreaterThanEqual(double soldeMini);

	List<CompteEntity> findBySoldeBetween(double soldeMini,double soldeMaxi);

	@Query("SELECT c FROM CompteEntity c WHERE c.solde >= ?1 and c.solde <= ?2")
	List<CompteEntity> findBySoldeEntre(double soldeMini,double soldeMaxi);

}
