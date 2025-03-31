package tp.appliSpring.explicit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.explicit.beans.Encadreur;
import tp.appliSpring.explicit.conf.ExempleConfigExplicite;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfigExplicite.class})
@ActiveProfiles("maj")
public class TestEncadreur {

    private static Logger logger = LoggerFactory.getLogger(TestEncadreur.class);

    @Autowired
    private Encadreur encadreur;


    @Value("${preferences.prefixe:#}")
    private String prefixe;

    @Test
    public void testEncadreur() {
        String res = encadreur.encadrer("java");
        logger.debug("res="+res);
        assertEquals(prefixe + "JAVA]]]", res);
        //assertEquals("** java **", res,0.00000001);
    }
}
