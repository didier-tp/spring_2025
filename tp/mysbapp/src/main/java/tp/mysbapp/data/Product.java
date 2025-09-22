package tp.mysbapp.data;

import lombok.*;

//classe de données "métier" (potentiel DTO)
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Product {
    private Long id;
	private String label;
	private Double price;

}
