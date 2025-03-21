package tp.appliSpring.bank.web.api.rest;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tp.appliSpring.bank.core.model.Compte;
import tp.appliSpring.bank.core.service.ServiceCompte;
import tp.appliSpring.bank.persistence.entity.CompteEntity;
import tp.appliSpring.bank.persistence.repository.CompteRepository;
import tp.appliSpring.bank.web.api.dto.CompteToCreate;
import tp.appliSpring.generic.dto.ApiError;
import tp.appliSpring.generic.dto.MessageDto;
import tp.appliSpring.generic.exception.EntityNotFoundException;


@RestController //@Component de type controller d'api rest
@RequestMapping(value="/rest/api-bank/v1/comptes" , headers="Accept=application/json")
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST ,
		             RequestMethod.PATCH , RequestMethod.DELETE , RequestMethod.PUT})
public class CompteRestCtrl {

	/*
	//Code potentiellement en erreur à ne pas reproduire:
	@Autowired
	private CompteRepository compteRepository;

	@GetMapping("/{id}")
	public CompteEntity badVersionWithoutDtoForGetCompteById(@PathVariable("id") long numeroCompte) {
		return compteRepository.findById( numeroCompte).get();
		//NB: plantage si pas de @JsonIgnore et généralement sans_DTO = très mauvaise pratique
	}
    */


	private ServiceCompte serviceCompte;

	@Autowired
	public CompteRestCtrl(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}

	//Get By ID
	//V1 avec DTO et V3 (avec automatisme ExceptionHandler)
	//declencher en mode GET avec
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1 ou 2
	@GetMapping("/{id}")
	@ApiResponse(responseCode = "200", ref = "#/components/responses/CompteResponse")
	@ApiResponse(responseCode = "404", ref = "#/components/responses/NotFoundErrorResponse")
	@ApiResponse(responseCode = "500", ref = "#/components/responses/InternalServerErrorResponse")
	public Compte getCompteById(@PathVariable("id") long numeroCompte) {
		return serviceCompte.searchById(numeroCompte);
		//NB: l'objet retourné sera automatiquement converti au format json
	}

	
    /*
	//V2 avec ResponseEntity<?> mais sans ExceptionHandler
	//declencher en mode GET avec
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1 ou 2
	@GetMapping("/{id}")
	public ResponseEntity<?> getCompteById(@PathVariable("id") long numeroCompte) {
				try {
					Compte compte = serviceCompte.searchById( numeroCompte);
					//return new ResponseEntity<Compte>(compte, HttpStatus.OK);
					return ResponseEntity.ok(compte);
				} catch (EntityNotFoundException e) {
					//e.printStackTrace();
					System.err.println(e.getMessage());
					//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							             .body(new MessageDto(e.getMessage()));
				}
	}
   */

	//GET Multiple
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes?soldeMini=50
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes?numClient=1
	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes?soldeMini=50&critere2=val2&critere3=val3
	@GetMapping("")
	@ApiResponse(responseCode = "200", ref = "#/components/responses/CompteResponse")
	@ApiResponse(responseCode = "500", ref = "#/components/responses/InternalServerErrorResponse")
	public List<Compte> getComptesByCriteria(@RequestParam(value = "soldeMini", required = false) Double soldeMini,
											 @RequestParam(value = "numClient", required = false) Long numClient) {
		List<Compte> listeCompte = null; //new ArrayList<>();
		if (soldeMini != null)
			listeCompte = serviceCompte.searchWithMinimumBalance(soldeMini);
		if (numClient != null)
			listeCompte = serviceCompte.searchCustomerAccounts(numClient);
		if (soldeMini == null && numClient == null)
			listeCompte = serviceCompte.searchAll();
		return listeCompte;
	}

	//appelé en mode POST
	//avec url = http://localhost:8181/appliSpring/rest/api-bank/v1/comptes
	//avec dans la partie "body" de la requête
	// { "numero" : null , "label" : "comptequiVaBien" , "solde" : 50.0 }
	@Operation(summary = "post a new account to be created")
	@ApiResponse(responseCode = "201", ref = "#/components/responses/CreatedCompteResponse")
	@ApiResponse(responseCode = "500", ref = "#/components/responses/InternalServerErrorResponse")
	@PostMapping("")
	//@PreAuthorize("hasAuthority('SCOPE_resource.write')")
	@PreAuthorize("hasAuthority('SCOPE_resource.write') or hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<?> postCompte(@Valid @RequestBody CompteToCreate compte) {
		Compte compteSauvegarde = serviceCompte.create(compte);  //avec numero auto_incrémenté
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(compteSauvegarde.getNumero()).toUri();
		//return ResponseEntity.created(location).build(); //return 201/CREATED , no body but URI to find added account
		return ResponseEntity.created(location).body(compteSauvegarde);//return 201/CREATED with account  AND with URI to find added account
       /* ou bien encore
		return ResponseEntity.ok()
				.headers(responseHeadersWithLocation)
				.body(compteSauvegarde); //avec numero auto_incrémenté
		*/
	}

	//appelé en mode PUT
	//avec url = http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1
	//avec dans la partie "body" de la requête
	// { "numero" : 1 , "label" : "libelleModifie" , "solde" : 120.0  }
	@PutMapping("/{id}")
	@ApiResponse(responseCode = "404", ref = "#/components/responses/NotFoundErrorResponse")
	@ApiResponse(responseCode = "204", ref = "#/components/responses/NoContentResponse")
	@ApiResponse(responseCode = "500", ref = "#/components/responses/InternalServerErrorResponse")
	//@PreAuthorize("hasAuthority('SCOPE_resource.write')")
	@PreAuthorize("hasAuthority('SCOPE_resource.write') or hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<Compte> putCompte(@RequestBody Compte compte, @PathVariable("id") Long idToUpdate) {
		compte.setNumero(idToUpdate);
		Compte compteMisAJour = serviceCompte.update(compte);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //204 : OK sans aucun message dans partie body
		//exception handler may return NOT_FOUND or INTERNAL_SERVER_ERROR
	}

	//http://localhost:8181/appliSpring/rest/api-bank/v1/comptes/1 ou 2
	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "404", ref = "#/components/responses/NotFoundErrorResponse")
	@ApiResponse(responseCode = "204", ref = "#/components/responses/NoContentResponse")
	@ApiResponse(responseCode = "500", ref = "#/components/responses/InternalServerErrorResponse")
	//@PreAuthorize("hasAuthority('SCOPE_resource.delete')")
	@PreAuthorize("hasAuthority('SCOPE_resource.delete') or hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<?> deleteCompteById(@PathVariable("id") Long id) {
		serviceCompte.removeById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //NO_CONTENT = OK mais sans message
		//return ResponseEntity.ok(new MessageDto("compte with id=" + id + " successfully deleted")); //200/OK + message
		//exception handler may return NOT_FOUND or INTERNAL_SERVER_ERROR
	}
}


	//******************** OpenApi (swagger3) examples: **************
	/*

	@Operation(summary= "post a new account to be created")
	@ApiResponses({
			@ApiResponse(responseCode = "201" ,
	          content = {@Content(mediaType = "application/json",
			schema = @Schema(ref = "#/components/schemas/CompteSchema")
	)}),
	@ApiResponse(responseCode = "500" ,
		content = {@Content(schema = @Schema(ref = "#/components/schemas/ApiErrorSchema"))})
})
@PostMapping("")
public ResponseEntity<?> postCompte(@Valid @RequestBody CompteToCreate compte) {
	*/

	/*
	@Operation(summary= "post a new account to be created")
	@ApiResponses({
			@ApiResponse(responseCode = "201" ,  , description = "Created"
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Compte.class )
					)}),
			@ApiResponse(responseCode = "500" , description = "Internal Server Error",
					content = {@Content(schema = @Schema(implementation = ApiError.class))})
	})
	@PostMapping("")
	public ResponseEntity<?> postCompte(@Valid @RequestBody CompteToCreate compte) {
	*/


