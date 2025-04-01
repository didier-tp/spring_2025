package tp.appliSpring.core.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.bank.persistence.entity.OperationEntity;
import tp.appliSpring.bank.persistence.repository.CompteRepository;
import tp.appliSpring.bank.persistence.repository.OperationRepository;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({  "dev" })
public class TestOperationDaoSql {

    private static Logger logger = LoggerFactory.getLogger(TestOperationDaoSql.class);

    @Autowired
    private CompteRepository daoCompte; //pour aider à tester

    @Autowired
    private OperationRepository daoOperation;  //à tester

    @Test
    @Sql({"/import_comptes_with_operations.sql"})
    public void testFindForCompteWithDateBetween() throws Exception{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<OperationEntity> operations = daoOperation.findForCompteWithDateBetween(1002L,
                dateFormat.parse("2025-02-01") ,
                dateFormat.parse("2025-03-15"));
        assertTrue(operations.size()==2);
        logger.debug("operations entre 2025-02-01 et 2025-03-15 ="+operations);

    }


}
