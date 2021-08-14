package futureofmedia.trial.dto;

import futureofmedia.trial.enm.StatusEnum;
import futureofmedia.trial.entity.ContactPersonEntity;
import futureofmedia.trial.validator.ContactPhoneNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class ContactPersonDto {
    private StatusEnum status;
    private int id;
    @NotNull(message = "The first name is required")
    private String firstName;
    @NotNull(message = "The last name is required")
    private String lastName;
    @NotNull(message = "The email is required")
    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    @NotNull(message = "The phone number is required")
    @ContactPhoneNumber(message = "Hibás telefonszám")
    private String phoneNumber;
    private String description;
    private Date createdAt;
    private Date modifiedAt;
    @Valid
    @NotNull(message = "The company is required")
    private CompanyDto companyByCompanyId;

    public ContactPersonEntity toContactPersonEntity() {
        ContactPersonEntity contactPersonEntity = new ContactPersonEntity();
        contactPersonEntity.setStatus(StatusEnum.active);
        contactPersonEntity.setId(id);
        contactPersonEntity.setFirstName(firstName);
        contactPersonEntity.setLastName(lastName);
        contactPersonEntity.setEmail(email);
        contactPersonEntity.setPhoneNumber(phoneNumber);
        contactPersonEntity.setDescription(description);
        contactPersonEntity.setCompanyByCompanyId(companyByCompanyId.toCompanyEntity());
        return contactPersonEntity;
    }
}
