package tp.appliSpring.explicit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.explicit.beans.Encadreur;
import tp.appliSpring.explicit.conf.ExempleConfigExplicite;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfigExplicite.class})
@ActiveProfiles({"maj"})
public class TestEncadreurMaj {

    private static Logger logger = LoggerFactory.getLogger(TestEncadreurMaj.class);

    @Autowired
    private Encadreur encadreur;

    @Test

    public void testEncadrer() {
        String res = encadreur.encadrer("java");
        logger.debug("pour chaine=java , res="+res);
        assertEquals("[[[JAVA]]]", res);
    }
}
