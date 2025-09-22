package tp.sbapp.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductToCreate extends Product {
    public ProductToCreate(Long id, String label, Double price) {
        super(null, label,price);
    }


    @Schema(hidden = true)
    public Long getId(){
       return  super.getId();
    }

    public ProductToCreate() {
        this(null,null,null);
    }
}
