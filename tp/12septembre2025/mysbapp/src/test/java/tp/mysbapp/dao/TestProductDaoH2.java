package tp.mysbapp.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import lombok.RequiredArgsConstructor;
import tp.mysbapp.entity.ProductEntity;


@SpringBootTest
@ActiveProfiles({"dev_h2","ddl_auto"})
@RequiredArgsConstructor()//pour demander à lombok de générer un constructeur
//avec toutes les choses "final" , et donc constructeur qui fait de l'injection de dépendance
public class TestProductDaoH2 {
	private static Logger logger = LoggerFactory.getLogger(TestProductDaoH2.class);


	private final ProductDao productDao ; //à tester
	
	
	@Test
	@Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
	public void testFind() {
		//List<ProductEntity> products = productDao.findAll();
		//List<ProductEntity> products = productDao.findByPriceLessThanEqual(3.0);
		List<ProductEntity> products = productDao.findByPriceMaxi(3.0);
		assertTrue(products.size()>=2);
		logger.debug("products="+products);
	}
}
