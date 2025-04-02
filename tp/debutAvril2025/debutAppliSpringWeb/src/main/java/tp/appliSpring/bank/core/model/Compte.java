package tp.appliSpring.bank.core.model;

import lombok.*;
import tp.appliSpring.generic.model.WithId;

@ToString @NoArgsConstructor
@Getter @Setter
//@RequiredArgsConstructor
public class Compte implements WithId<Long> {
    private Long numero;

    private String label;

    private Double solde;

    public Compte(Long numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }

   /* public Compte() {
        this(null,null,null);
    }*/

    @Override
    public Long extractId() {
        return this.numero;
    }
/*
    @Override
    public String toString() {
        return "Compte{" +
                "numero=" + numero +
                ", label='" + label + '\'' +
                ", solde=" + solde +
                '}';
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
    */

}
