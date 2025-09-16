package tp.mysbapp.data;

//classe de données "métier" (potentiel DTO)
public class Product {
    private Long id;
	private String label;
	private Double price;
	
	
	//+get/set, constructor , toString
	@Override
	public String toString() {
		return "Product [id=" + id + ", label=" + label + ", price=" + price + "]";
	}

	public Product(Long id, String label, Double price) {
		super();
		this.id = id;
		this.label = label;
		this.price = price;
	}
	
	public Product() {
		this(null,null,null);
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
