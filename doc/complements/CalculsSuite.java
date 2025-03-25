package tp.calculs;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

//@RunWith(JUnitPlatform.class) will be deprecated , use @Suite instead
//but @Suite not already supported by some old IDE (ex: ok with eclipse 2023-03)
@Suite
@SelectClasses({
	TestCalculsSimples.class,
	TestEmptySerie.class,
	TestSerie.class
})
public class CalculsSuite {
}

/*
mvn test -> lance tous tests dont les noms de classes commencent ou se terminent par "Test"
mvn test -Dtest=XxxTest -> lance que le test "XxxTest"
mvn test -Dtest=XyzSuite -> lance la suite de tests XyzSuite
mvn test -Dtest=*xxTest -> lance tous les tests finissants par "xxTest"
NB:
par défaut , les tests unitaires sont systématiquement déclenchés lors d'un build
ordinaire (ex: mvn package)
L'option "-DskipTests=true " de mvn permet d'annuler/sauter l'exécution des tests.
*/
