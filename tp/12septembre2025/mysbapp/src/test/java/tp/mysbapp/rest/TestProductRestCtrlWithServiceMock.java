package tp.mysbapp.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import tp.mysbapp.data.Product;
import tp.mysbapp.service.ProductService;

@WebMvcTest(ProductRestCtrl.class)
public class TestProductRestCtrlWithServiceMock {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private ProductService productService; // not real implementation but mock to configure .

	@Test // à lancer sans le profile withSecurity
	public void testAllProductsWithMockOfProductService() {
		// préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à
		// tester):
		List<Product> products = new ArrayList<>();
		products.add(new Product(null, "styloA", 2.2));
		products.add(new Product(null, "styloB", 2.5));
		Mockito.when(productService.findAll()).thenReturn(products);
		try {
			MvcResult mvcResult = mvc
					.perform(get("/rest/api-product/v1/products")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
					.andExpect(jsonPath("$[0].label", is("styloA")))
					.andExpect(jsonPath("$[1].price", is(2.5)))
					.andReturn();
			System.out.println(">>>>>>>>> jsonResult=" + mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
