package tp.appliSpring.bank.persistence.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="COMPTE")
@NamedQuery(name = "CompteEntity.findWithOperations" ,
           query="SELECT cpt FROM CompteEntity cpt LEFT JOIN FETCH cpt.operations WHERE cpt.numero = ?1")
@Getter
@Setter
public class CompteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="numero")
    private Long numero;
    
    @Column(name="label" , length = 64)
    private String label;
    
    private Double solde;
    
    @OneToMany(mappedBy = "compte" , fetch = FetchType.LAZY ,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<OperationEntity> operations = new ArrayList<>(); //+get/set

	@ManyToMany(mappedBy = "comptes" , cascade = CascadeType.DETACH) // coté secondaire avec mappedBy="nomJavaRelationInverse"
	private List<ClientEntity> clients = new ArrayList<>();

  //+get/set , constructeur , toString()
    
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}


	public CompteEntity(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	public CompteEntity() {
		super();
	}
	
}