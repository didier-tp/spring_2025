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
@ActiveProfiles({"dev_h2","ddl_auto"})
//Attention , la base h2 n'est pas gérée par un serveur .
//et il peut y avoir un fichier verrouillé qui bloque le bon lancement
//pour lancer le test, il faut préalablement arrêter l'appli (bouton rouge)
public class TestProductDaoDevH2 {
    private static Logger logger = LoggerFactory.getLogger(TestProductDaoDevH2.class);

    @Autowired
    private ProductDao productDao ; //à tester

    @Test
    @Sql({"/import_products.sql"})//import_products.sql dans src/test/resources
    public void test1(){
        List<ProductEntity> products = productDao.findAll();
        assertTrue(products.size()>=4);
        logger.debug("products="+products);
    }
}
