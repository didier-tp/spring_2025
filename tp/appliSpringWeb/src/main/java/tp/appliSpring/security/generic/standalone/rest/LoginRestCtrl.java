package tp.appliSpring.security.generic.standalone.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliSpring.security.generic.standalone.JwtTokenProvider;
import tp.appliSpring.security.generic.standalone.dto.LoginRequest;
import tp.appliSpring.security.generic.standalone.dto.LoginResponse;


@RestController
@CrossOrigin("*")
@Profile("withSecurity & withoutOAuth2")
@RequestMapping(value="/rest/api-auth/v1/standalone-jwt-auth" , headers="Accept=application/json")
public class LoginRestCtrl {
	
	Logger logger = LoggerFactory.getLogger(LoginRestCtrl.class);
	
	
	@Autowired(required = true)
	/*@Qualifier("rest")*/  //NB: it may be an alias of global/unique AuthenticationManager (without @Qualifier)
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	//http://localhost:8080/..../rest/api-login/public/login
	//avec { "username" : "user1" , "password" : "pwd1" }
	//en retour { "message" :  "..." , "token" ; "..." , ...}
	@PostMapping("")
	public ResponseEntity<LoginResponse> postLogin(@RequestBody LoginRequest loginRequest){
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUsername(loginRequest.getUsername());
		/* if(loginRequest.getUsername().equals("user1") && 
				loginRequest.getPassword().equals("pwd1")) */
		try {
		Authentication authentication = null;
		//vérifier le username/password avec SpringSecurity:
		authentication=authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		                loginRequest.getUsername(),
		                loginRequest.getPassword()
		        ));//authenticate() soulève une exception si mauvais username ou password
			loginResponse.setOk(true);
			loginResponse.setMessage("successful login");
			loginResponse.setToken(jwtTokenProvider.generateToken(authentication));
		}catch(Exception ex) {
			logger.error("echec auth :" + ex.getMessage());
			ex.printStackTrace();
			loginResponse.setOk(false);
			loginResponse.setMessage("login failed");
			loginResponse.setToken(null);
			return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
	}

}

