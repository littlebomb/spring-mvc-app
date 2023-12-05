import org.example.controllers.RestApiController;
import org.example.models.Product;
import org.example.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class IntegrationTests {
    @Autowired
    private RestApiController restApiController;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void restApiIsNotNull() throws Exception {
        assertNotNull(restApiController);
    }
    @Test
    public void listProducts() throws Exception{
        this.mockMvc.perform(post("/api/products/add")
                    .contentType(APPLICATION_JSON)
                    .content("{\"productName\": \"grapes\""))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("grapes")));
    }

    @Test
    public void getProductById() throws Exception {
        long id = 13L;
        Optional<Product> product = productRepository.findById(id);

        this.mockMvc.perform(get("/api/products/"+id)
                    .contentType(APPLICATION_JSON)
                    .content("{\"id\":13,\"name\":\"banana\",\"isBought\":\"false\"}"))
                .andDo(print())
                .andExpect(content().string(containsString(product.get().getName())))
                .andExpect(status().isOk());
    }
}
