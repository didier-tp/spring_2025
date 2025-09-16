package tp.mysbapp.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import tp.mysbapp.entity.ProductEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles({"dev_sgbd","ddl_auto"})
//choix de profils plus priotaire que ce qui est dans application.properties et spring.profiles.active

public class TestProductDaoDevSgbd {
    private static Logger logger = LoggerFactory.getLogger(TestProductDaoDevSgbd.class);

    @Autowired
    private ProductDao productDao; //à tester

    @Test
    @Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
    public void test1(){
        //List<ProductEntity> products = productDao.findAll();
        List<ProductEntity> products = productDao.findByPriceLessThanEqual(3);
        assertTrue(products.size()>=2); //2 produits sur les 4 ont un prix <=3
        logger.debug("products avec prix plus petit ou egal a 3="+products);
    }

    @Test
    @Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
    public void test2(){
        List<ProductEntity> products = productDao.findByMinimumPrice(3);
        assertTrue(products.size()>=2); //2 produits sur les 4 ont un prix >=3
        logger.debug("products avec prix plus grand ou egal a 3="+products);
    }
}
