package tp.appliSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@Entity
//@Table(name="compte")
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//auto_increment coté base (h2 ou bien mysql ou bien postgres ) et remobntée en memoire dans .numero
	//@Column(name="numero")
	private Long numero;
	
	@Column(name="label", length = 64) //cohérent avec VARCHAR(64)
	private String label;
	
	private Double solde;

	//+get/set, constructeur , .toString()
	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

}
