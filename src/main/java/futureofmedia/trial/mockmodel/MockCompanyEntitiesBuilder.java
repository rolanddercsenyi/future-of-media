package futureofmedia.trial.mockmodel;

import futureofmedia.trial.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class MockCompanyEntitiesBuilder {

    private final CompanyEntity companyEntity;

    public MockCompanyEntitiesBuilder() {
        companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setName("Company #1");
    }

    public CompanyEntity build() {
        return companyEntity;
    }
}
