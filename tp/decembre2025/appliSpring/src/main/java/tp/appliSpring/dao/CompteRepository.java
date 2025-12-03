package tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Compte;

/*
 CompteRepository est une interface de DAO liée à Spring-Data-Jpa
 et la classe d'implémentation basée sur EntityManager sera générée automatiquement
 */
public interface CompteRepository extends JpaRepository<Compte,Long>{
   /*
    principales méthodes héritées:
    .save()
    .findAll()
    .findById()
    .deleteById()
    ...
    */
}
