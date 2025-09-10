package tp.sbapp.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
public class TestProductDaoDevSgbd {
	
	private static Logger logger = LoggerFactory.getLogger(TestProductDaoDevSgbd.class);
	
	@Autowired 
	private ProductDao productDao ; //à tester
	
	@Test
	@Sql({"/import_products.sql"})
	public void test1(){
		List<ProductEntity> products = productDao.findAll();
		assertTrue(products.size()>=4);
		logger.debug("products="+products);
	}
}
