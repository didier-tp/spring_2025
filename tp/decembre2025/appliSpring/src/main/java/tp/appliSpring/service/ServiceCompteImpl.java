package tp.appliSpring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.dao.CompteRepository;
import tp.appliSpring.entity.Compte;

@Service //@Component de type service métier (pris en charge par Spring)
//@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	private final CompteRepository compteRepository;
	
	//injection de dépendance par constructeur
	public ServiceCompteImpl(CompteRepository compteRepository) {
		this.compteRepository = compteRepository;
	}

	@Override
	//@Transactional
	public Compte sauvegarderCompte(Compte c) {
		return this.compteRepository.save(c); 
	}

	@Override
	public Compte rechercherCompteParId(long numCpt) {
		return this.compteRepository.findById(numCpt).orElse(null);
	}

	@Override
	@Transactional
	public void transfer(double montant, long numCptDeb, long numCptCred) {
		Compte cptDeb =  this.compteRepository.findById(numCptDeb).orElse(null);
		cptDeb.setSolde(cptDeb.getSolde() - montant );
		this.compteRepository.save(cptDeb); 
		
		Compte cptCred =  this.compteRepository.findById(numCptCred).orElse(null);
		cptCred.setSolde(cptCred.getSolde() + montant );
		this.compteRepository.save(cptCred);
	}

}
