package futureofmedia.trial.controller;

import futureofmedia.trial.entity.CompanyEntity;
import futureofmedia.trial.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @MockBean
    CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_return_company_entities_without_token() throws Exception {
        List<CompanyEntity> companyEntityList = new ArrayList<>();

        when(companyService.getCompanyEntities()).thenReturn(companyEntityList);

        mockMvc.perform(get("/api/company"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void it_should_return_company_entities_with_token() throws Exception {
        List<CompanyEntity> companyEntityList = new ArrayList<>();
        LoginController loginController = new LoginController();
        String token = loginController.getJWTToken("tesztElek");
        when(companyService.getCompanyEntities()).thenReturn(companyEntityList);

        mockMvc.perform(get("/api/company").header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
