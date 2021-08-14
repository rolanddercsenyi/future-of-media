package futureofmedia.trial.service;

import futureofmedia.trial.dto.ContactPersonDto;
import futureofmedia.trial.enm.StatusEnum;
import futureofmedia.trial.entity.ContactPersonEntity;
import futureofmedia.trial.interfaces.ContactPersonInterface;
import futureofmedia.trial.repository.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * ContactPerson handler service
 */
@Service
public class ContactPersonService implements ContactPersonInterface {

    private final ContactPersonRepository contactPersonRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public ContactPersonService(ContactPersonRepository contactPersonRepository, JavaMailSender mailSender) {
        this.contactPersonRepository = contactPersonRepository;
        this.mailSender = mailSender;
    }

    /**
     * Kapcsolattartó lista szűrése, csak a status = active sorokat adja vissza
     *
     * @param firstName     keresztnév
     * @param lastName      vezetéknév
     * @param email         email cím
     * @param phoneNumber   telefonszám
     * @param description   leírás
     * @param pageNr        lapozóhoz oldalszám
     * @param size          lapozóhoz oldalak száma
     * @param sortColumn    rendezni kivánt oszlop
     * @param sortDirection rendezés iránya
     * @return szűrt Kapcsolattartó lista
     */
    public Page<ContactPersonEntity> getContactPersonPage(String firstName, String lastName, String email, String phoneNumber, String description, int pageNr, int size, String sortColumn, String sortDirection) {
        return contactPersonRepository.findAllIsActive(firstName, lastName, email, phoneNumber, description, this.setPage(pageNr, size, sortColumn, sortDirection));
    }

    /**
     * Kapcsolattartó lista szűrése ID által(1 Entityvel tér vissza)
     *
     * @param id Kapcsolattartó azonosítója
     * @return kiválasztott kapcsolattartó
     */
    public ContactPersonEntity getContactPersonById(int id) {
        return contactPersonRepository.findAllById(id);
    }

    /**
     * ID alapján szűrt kapcsolattartó törlése(status = deleted)
     *
     * @param id Kapcsolattartó azonosítója
     * @return törölt kapcsolattartó adatai
     */
    public ContactPersonEntity deleteContactPersonBy(int id) {
        ContactPersonEntity personById = contactPersonRepository.findAllById(id);
        personById.setStatus(StatusEnum.deleted);
        contactPersonRepository.save(personById);
        return personById;
    }

    /**
     * Lapozó beállítása
     *
     * @param pageNr oldal száma
     * @param size   tételek száma egy oldalon
     * @param column rendezendő oszlop neve
     * @param order  rendezés iránya
     * @return page settings
     */
    private Pageable setPage(int pageNr, int size, String column, String order) {
        Pageable page;
        if (order.equals("descending")) {
            page = PageRequest.of(pageNr, size, Sort.by(column).descending());
        } else {
            page = PageRequest.of(pageNr, size, Sort.by(column).ascending());
        }

        return page;
    }

    /**
     * Kiválasztott kapcsolattartó létrehozása, vagy szerkesztése, függ attól, hogy kap-e ID-t, vagy van-e olyan ID már az adatbázisban
     *
     * @param contactPersonDto kapcsolattartó adatai
     * @return létrehozott, vagy szerkesztett kapcsolattartó adatai
     */
    public ContactPersonEntity createContactPerson(ContactPersonDto contactPersonDto) {
        ContactPersonEntity contactPersonEntity = contactPersonRepository.save(contactPersonDto.toContactPersonEntity());
        this.sendEmail(contactPersonEntity);
        return contactPersonEntity;
    }

    /**
     * Email küldése
     *
     * @param contactPersonEntity Kapcsolattartó adatai
     */
    private void sendEmail(ContactPersonEntity contactPersonEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contactPersonEntity.getEmail());
        message.setSubject("Kapcsolattartó adatai létrehozva/szerkesztve");
        message.setText("Üdv, " + contactPersonEntity.getFirstName() + "!");
        mailSender.send(message);
    }
}
