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
//@SpringBootTest(classes= {SbappApplication.class})//reprendre la configuration de la classe principale , commportement par defaut
@ActiveProfiles({"dev_h2","ddl_auto"})
@Slf4j
public class TestProductDaoDevH2 {
	
	@Autowired 
	private ProductDao productDao ; //à tester
	
	@Test
	@Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
	public void test1(){
		List<ProductEntity> products = productDao.findAll();
		assertTrue(products.size()>=4);
		log.debug("products="+products);
	}
}
