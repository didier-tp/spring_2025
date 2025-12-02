package tp.appliSpring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliSpring.entiy.Compte;

@Repository //@Component de type DAO
@Transactional //en version spring
public class DaoCompteImpl implements DaoCompte {
	
	@PersistenceContext() //pour intialiser/fabriquer l'objet entityManager en fonction de la config de l'appli (...properties)
	private EntityManager entityManager;

	@Override
	public List<Compte> findAll() {
		return entityManager.createQuery("SELECT c FROM Compte c",Compte.class).getResultList();
	}

	@Override
	public Compte save(Compte c) {
		//.save() au sens saveOrUpdate
		if(c.getNumero()==null)
			entityManager.persist(c); //INSERT INTO et auto_incr
		else
			entityManager.merge(c); //UPDATE sql
		return c; //en retour l'objet sauvegardé (avec quelquefois la clef primaire auto incrémentée)
	}

	
}
