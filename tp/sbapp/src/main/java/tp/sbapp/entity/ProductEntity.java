package tp.sbapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64)
	private String label;
	
	private Double price;
	
	//+get/set, constructor , toString
	public ProductEntity(Long id, String label, Double price) {
		super();
		this.id = id;
		this.label = label;
		this.price = price;
	}
	
	public ProductEntity() {
		this(null,null,null);
	}
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", label=" + label + ", price=" + price + "]";
	}

	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
