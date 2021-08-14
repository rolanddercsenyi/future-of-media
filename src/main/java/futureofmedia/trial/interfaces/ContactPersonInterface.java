package futureofmedia.trial.interfaces;

import futureofmedia.trial.dto.ContactPersonDto;
import futureofmedia.trial.entity.ContactPersonEntity;
import org.springframework.data.domain.Page;

public interface ContactPersonInterface {
    Page<ContactPersonEntity> getContactPersonPage(String firstName, String lastName, String email, String phoneNumber, String description, int pageNr, int size, String sortColumn, String sortDirection);

    ContactPersonEntity getContactPersonById(int id);

    ContactPersonEntity deleteContactPersonBy(int id);

    ContactPersonEntity createContactPerson(ContactPersonDto contactPersonDto);
}
