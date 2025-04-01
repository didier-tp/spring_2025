package tp.appliSpring.exemple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {ExempleConfig.class})
@ActiveProfiles("perf")
public class TestMonCalculateur {
	
	private static Logger logger = LoggerFactory.getLogger(TestMonCalculateur.class);
	
	@Autowired @Qualifier("monCalculateurCarre") //pour injection par nom
	//private MonCalculateurCarre monCalculateur; //à tester
	private MonCalculateur monCalculateur; //à tester
	
	@Test
	public void testCalculer() {
		double res = monCalculateur.calculer(4);
		logger.debug("pour x=4 , res="+res);
		assertEquals(16.0, res,0.00000001);
	}

}
