package tp.appliSpring.service;

import tp.appliSpring.entity.Compte;

public interface ServiceCompte {
	public Compte sauvegarderCompte(Compte c);
	public Compte rechercherCompteParId(long numCpt);
    public void transfer(double montant,long numCptDeb,long numCptCred);
    //...
}
