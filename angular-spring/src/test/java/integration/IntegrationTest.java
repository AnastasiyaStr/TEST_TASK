package integration;


import com.javainuse.SpringBootHelloWorldApplication;
import com.javainuse.nastia.dao.Impl.EmployeeDaoJDBC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringBootHelloWorldApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class IntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    EmployeeDaoJDBC customerDaoJDBC;

    @Test
    public void OKIntegrationTest()
            throws Exception {
        mvc.perform(get("/employee").header("Authorization", "Basic amF2YWludXNlOnBhc3N3b3Jk")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void NotFoundIntegrationTest()
            throws Exception {
        mvc.perform(get("/employee/10000").header("Authorization", "Basic amF2YWludXNlOnBhc3N3b3Jk")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
}
