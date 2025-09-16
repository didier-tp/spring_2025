package tp.mysbapp.entity;

import jakarta.persistence.*;
import lombok.*;

//classe d'entité persistante (classe de données)
//avec annotations de JPA pour le lien avec la base de données

@Entity
@Table(name="product")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String label;

    //@Column(name="price") par defaut
    private Double price;

//+get/set, constructor , toString
//par assistant eclipse ou itelliJ ou bien via l'extension lombok
}
