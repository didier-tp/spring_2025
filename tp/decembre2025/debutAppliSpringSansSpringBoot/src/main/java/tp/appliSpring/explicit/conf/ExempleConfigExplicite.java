package tp.appliSpring.explicit.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import tp.appliSpring.explicit.beans.Encadreur;
import tp.appliSpring.explicit.beans.EncadreurImpl;
import tp.appliSpring.explicit.beans.Prefixeur;
import tp.appliSpring.explicit.beans.PrefixeurBasic;
import tp.appliSpring.explicit.beans.Suffixeur;
import tp.appliSpring.explicit.beans.SuffixeurBasic;

@Configuration
@PropertySource("classpath:exemples.properties")
public class ExempleConfigExplicite {

    //A faire en Tp:
    //phase 0 : faire fonctionner l'exemple tel quel

    //phase 1 : tenir compte des choix de prefixe et suffixe de exemples.properties
    //via @Value("${...:..}")
    //avec dans src/main/resources/exemples.properties
    //preferences.prefixe=>>>
    //preferences.suffixe=<<<
    //tester le comportement en modifiant les valeurs des prefixe et suuffixe dans le .properties

    //phase 2 : variante ...Basic en l'absence de profile "maj"
    //et vraiante ...Maj si présence du profile "maj"
    //tester le comportement en activant ou pas de profile "maj" en début de main()

	@Value("${preferences.prefixe:#}")//avec # comme valeur par défaut
	//@Value("${preferences.prefixe}") //sans valeur par défaut
	private String monPrefixe;
    //private String monPrefixe="#";

	@Value("${preferences.suffixe:#}")//avec # comme valeur par défaut
	//@Value("${preferences.suffixe}") //sans valeur par défaut
	private String monSuffixe;
    //private String monSuffixe="#";

    @Bean
    public Prefixeur prefixeur(){
        return new PrefixeurBasic(monPrefixe);
    }


    @Bean
    public Suffixeur suffixeur(){
        return new SuffixeurBasic(monSuffixe);
    }

    @Bean
    public Encadreur encadreurSpring( Prefixeur prefixeurSpring , Suffixeur suffixeurSpring){
        //EncadreurImpl encadreurImpl =  new EncadreurImpl(prefixeurSpring,suffixeurSpring);
        EncadreurImpl encadreurImpl =  new EncadreurImpl();
        encadreurImpl.setPrefixeur(prefixeurSpring);
        encadreurImpl.setSuffixeur(suffixeurSpring);
        return encadreurImpl;
    }
}
