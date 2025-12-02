package tp.appliSpring.dao;

import java.util.List;

import tp.appliSpring.entiy.Compte;

public interface DaoCompte {
	
	List<Compte> findAll();
	Compte save(Compte c); //au sens saveOrUpdate
	//...

}
