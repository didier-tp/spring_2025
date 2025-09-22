package tp.sbapp.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tp.sbapp.data.Product;
import tp.sbapp.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring") //for @Autowired
public interface MyMapStructMapper {

    MyMapStructMapper INSTANCE = Mappers.getMapper( MyMapStructMapper.class );

    //@Mapping(target="price", source="prix_or_price") //ex si noms différents
    ProductEntity productToProductEntity(Product product);

    List<ProductEntity> productListToProductEntityList(List<Product> products);

    Product productEntityToProduct(ProductEntity product);

    List<Product> productEntityListToProductList(List<ProductEntity> products);
}
