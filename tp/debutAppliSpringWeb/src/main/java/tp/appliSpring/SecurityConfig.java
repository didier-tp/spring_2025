package tp.appliSpring;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@Configuration
@Profile("withSecurity")
@EnableMethodSecurity()//pour que le test @PreAuthorize("hasRole('ADMIN')") puisse bien fonctionner

//Automatic import (in .security sub package):
//@Import(SecurityConfigForRest)
//@Import(SecurityConfigForSite)
//@Import(SecurityConfigForOther)
public class SecurityConfig {
}
