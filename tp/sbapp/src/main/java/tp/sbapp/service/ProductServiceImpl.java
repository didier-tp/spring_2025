package tp.sbapp.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.sbapp.dao.ProductDao;
import tp.sbapp.data.Product;
import tp.sbapp.entity.ProductEntity;
import tp.sbapp.util.MyMapStructMapper;

@Service
@Transactional
@RequiredArgsConstructor //pour injection de dépendances via constructeur (final fields)
public class ProductServiceImpl implements ProductService{
	
	//@Autowired
	private final ProductDao productDao;

	//@Autowired
	private final MyMapStructMapper mapper;

	@Override
	public List<Product> findAll() {
		List<ProductEntity> listProdEntity = productDao.findAll();
		//return GenericMapper.MAPPER.map(listProdEntity,Product.class);
		return mapper.productEntityListToProductList(listProdEntity);
	}

	@Override
	public List<Product> findByPrixMini(double prixMini) {
		List<ProductEntity> listProdEntity = productDao.findByMinimumPrice(prixMini);
		return mapper.productEntityListToProductList(listProdEntity);
	}

	@Override
	public Optional<Product> findById(Long id) {
		Optional<ProductEntity> optProdEntity = productDao.findById(id);
		if(optProdEntity.isEmpty())
			return Optional.empty();
		else
		    return Optional.of(mapper.productEntityToProduct(optProdEntity.get()));
	}

	@Override
	public Product saveNew(Product p) {
		ProductEntity pE = mapper.productToProductEntity(p);
		ProductEntity savedPE =productDao.save(pE);
		return mapper.productEntityToProduct(savedPE);
	}

	@Override
	public void updateExisting(Product p) {
		ProductEntity pE = mapper.productToProductEntity(p);
		productDao.save(pE);
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

}
