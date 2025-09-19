package tp.sbapp.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tp.sbapp.data.Product;
import tp.sbapp.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring") //for @Autowired
public interface MyMapStructMapper {

    MyMapStructMapper INSTANCE = Mappers.getMapper( MyMapStructMapper.class );

    ProductEntity toProductEntity(Product product);
    List<ProductEntity> toProductEntityList(List<Product> products);
    Product fromProductEntity(ProductEntity product);
    List<Product> fromProductEntityList(List<ProductEntity> products);
}
