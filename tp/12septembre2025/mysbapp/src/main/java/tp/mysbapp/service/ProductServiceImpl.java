package tp.mysbapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.mysbapp.dao.ProductDao;
import tp.mysbapp.data.Product;
import tp.mysbapp.entity.ProductEntity;
import tp.mysbapp.util.GenericMapper;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findAll() {
		List<ProductEntity> listProdEntity = productDao.findAll();
		return GenericMapper.MAPPER.map(listProdEntity,Product.class);
	}

	@Override
	public Optional<Product> findById(Long id) {
		Optional<ProductEntity> optProdEntity = productDao.findById(id);
		if(optProdEntity.isEmpty())
			return Optional.empty();
		else
		    return Optional.of(GenericMapper.MAPPER.map(optProdEntity.get(),Product.class));
	}

	@Override
	public Product saveNew(Product p) {
		ProductEntity pE = GenericMapper.MAPPER.map(p,ProductEntity.class);
		productDao.save(pE);
		p.setId(pE.getId()); //stored auto_incr id 
		return p;
	}

	@Override
	public void updateExisting(Product p) {
		ProductEntity pE = GenericMapper.MAPPER.map(p,ProductEntity.class);
		productDao.save(pE);
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

}
