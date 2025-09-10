package tp.sbapp.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import tp.sbapp.data.Product;
import tp.sbapp.service.ProductService;

@WebMvcTest(controllers = { ProductRestCtrl.class } )
//@ActiveProfiles({"dev_h2","ddl_auto"}) //No profile required here (with mocked service)
//NB: @WebMvcTest  without service layer , service must be mocked !!!
public class TestProductRestCtrlWithServiceMock {
	
	private static Logger logger = LoggerFactory.getLogger(TestProductRestCtrlWithServiceMock.class);
	
	@Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductService productService; //not real implementation but mock to configure .

    @Test 
    public void testComptesDuClient1WithMockOfCompteService(){
    //préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à tester):
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L,"styloA",2.2));
        products.add(new Product(2L,"styloB",2.3));
        Mockito.when(productService.findAll()).thenReturn(products);
     //déclenchement de l'appel HTTP et verification de la réponse :  
        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-product/products")
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(2) ))
                            .andExpect(jsonPath("$[0].label", is("styloA") ))
                            .andExpect(jsonPath("$[1].price", is(2.3) ))
                            .andReturn();
            logger.debug(">>>>>>>>> all products, jsonResult="
                    +mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
        	logger.error(e.getMessage());
        }
    }
}
