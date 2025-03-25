package tp.appliSpring.explicit.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.WritableResource;
import org.springframework.core.io.Resource;
import tp.appliSpring.explicit.beans.*;

import java.nio.charset.Charset;

@Configuration
@PropertySource("classpath:exemples.properties")
public class ExempleConfigExplicite {

    private static Logger logger = LoggerFactory.getLogger(ExempleConfigExplicite.class);

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

    @Value("${preferences.prefixe:##}")
    private String monPrefixe;//="#";

    @Value("${preferences.suffixe:##}")
    private String monSuffixe;//="#";

    @Value("file:data/ficA.txt") //to read in project root directory
    private /*WritableResource*/ Resource fileATxtResource;

    @Bean(name="contenuFicA")
    public String contenuFicA() throws Exception {
        String textFileContent =  fileATxtResource.getContentAsString(Charset.defaultCharset());
        logger.info("textFileContent="+textFileContent);
        return textFileContent;
    }

    @Bean
    @Profile("!maj")
    public Prefixeur prefixeurBasic(){
        return new PrefixeurBasic(monPrefixe);
    }

    @Bean
    @Profile("maj")
    public Prefixeur prefixeurMaj(){
        return new PrefixeurMaj(monPrefixe);
    }


    @Bean
    @Profile("!maj")
    public Suffixeur suffixeurBasic(){
        return new SuffixeurBasic(monSuffixe);
    }

    @Bean
    @Profile("maj")
    public Suffixeur suffixeurMaj(){
        return new SuffixeurMaj(monSuffixe);
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
