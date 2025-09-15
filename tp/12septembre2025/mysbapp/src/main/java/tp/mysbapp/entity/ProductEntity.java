package tp.mysbapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product")
@Getter @Setter
public class ProductEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(length = 64)
private String label;

private Double price;
//+get/set, constructor , toString





@Override
public String toString() {
	return "ProductEntity [id=" + id + ", label=" + label + ", price=" + price + "]";
}


public ProductEntity() {
	super();
}


public ProductEntity(Long id, String label, Double price) {
	super();
	this.id = id;
	this.label = label;
	this.price = price;
}


}