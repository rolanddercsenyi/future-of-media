package futureofmedia.trial.dto;

import futureofmedia.trial.entity.CompanyEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CompanyDto {
    /**
     * cég azonosító, kötelező adat
     */
    @NotNull(message = "The Company id is required")
    private long id;

    /**
     * cág neve, kötelező adat
     */
    @NotNull(message = "The Company name is required")
    private String name;

    /**
     * Entity kezeléshez létrehozza a megfelelő objektumot
     *
     * @return companyEntity
     */
    public CompanyEntity toCompanyEntity() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(id);
        companyEntity.setName(name);
        return companyEntity;
    }
}
