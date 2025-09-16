package tp.mysbapp.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tp.mysbapp.data.Product;
import tp.mysbapp.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestCtrl.class)
public class RestProductRestCtrlWithServiceMock {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductService productService; //not real implementation but mock to configure .

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L,"Product 1", 1.99));
        products.add(new Product(2L,"Product 2", 2.7));
        Mockito.when(productService.findAll()).thenReturn(products);

        try {
            MvcResult mvcResult =
                    mvc.perform(get("/rest/api-product/products")
                                    .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$", hasSize(2) ))
                            .andExpect(jsonPath("$[0].label", is("Product 1") ))
                            .andExpect(jsonPath("$[1].price", is(2.7) ))
                            .andReturn();
            System.out.println(">>>>>>>>> jsonResult=" +mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
