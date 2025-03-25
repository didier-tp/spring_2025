package tp.appliSpring;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tp.appliSpring.exemple.TestMonCalculateur;
import tp.appliSpring.explicit.TestEncadreur;

@Suite
@SelectClasses({
        TestMonCalculateur.class,
        TestEncadreur.class
})
public class SuiteTestFondamentaux {
}

//mvn test -Dtest=tp.appliSpring.SuiteTestFondamentaux