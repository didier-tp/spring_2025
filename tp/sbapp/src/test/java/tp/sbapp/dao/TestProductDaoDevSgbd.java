package tp.sbapp.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import tp.sbapp.entity.ProductEntity;

@SpringBootTest
@ActiveProfiles({"dev_sgbd","ddl_auto"})
@Slf4j
public class TestProductDaoDevSgbd {

	
	@Autowired 
	private ProductDao productDao ; //à tester

	@Test
	@Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
	public void test1(){
		//List<ProductEntity> products = productDao.findAll();
		List<ProductEntity> products = productDao.findByPriceLessThanEqual(3);
		assertTrue(products.size()>=2); //2 produits sur les 4 ont un prix <=3
		log.debug("products avec prix plus petit ou egal a 3="+products);
	}

	@Test
	@Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
	public void test2(){
		List<ProductEntity> products = productDao.findByMinimumPrice(3);
		assertTrue(products.size()>=2); //2 produits sur les 4 ont un prix >=3
		log.debug("products avec prix plus grand ou egal a 3="+products);
	}
}
