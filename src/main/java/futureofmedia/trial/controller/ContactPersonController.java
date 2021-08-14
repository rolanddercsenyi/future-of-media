package futureofmedia.trial.controller;

import futureofmedia.trial.dto.ContactPersonDto;
import futureofmedia.trial.entity.ContactPersonEntity;
import futureofmedia.trial.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Kapcsolattartó kezelő
 */
@RestController
@RequestMapping("api")
public class ContactPersonController {

    private final ContactPersonService contactPersonService;

    @Autowired
    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
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
    @GetMapping(value = "/contact-person")
    public ResponseEntity<Page<ContactPersonEntity>> getContactPersonPage(
            @RequestParam(value = "first-name", required = false) String firstName,
            @RequestParam(value = "last-name", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone-number", required = false) String phoneNumber,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "page-number") int pageNr,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort-column") String sortColumn,
            @RequestParam(value = "sort-direction") String sortDirection
    ) {
        Page<ContactPersonEntity> contactPersonPage = contactPersonService.getContactPersonPage(firstName, lastName, email, phoneNumber, description, pageNr, size, sortColumn, sortDirection);
        return new ResponseEntity<>(contactPersonPage, HttpStatus.OK);
    }

    /**
     * Kapcsolattartó lista szűrése ID által(1 Entityvel tér vissza)
     *
     * @param id Kapcsolattartó azonosítója
     * @return kiválasztott kapcsolattartó
     */
    @GetMapping(value = "/contact-person/{id}")
    public ResponseEntity<ContactPersonEntity> getContactPersonById(@PathVariable(name = "id") int id) {
        ContactPersonEntity contactPersonEntity = contactPersonService.getContactPersonById(id);
        return new ResponseEntity<>(contactPersonEntity, HttpStatus.OK);
    }

    /**
     * Kiválasztott kapcsolattartó létrehozása, vagy szerkesztése, függ attól, hogy kap-e ID-t, vagy van-e olyan ID már az adatbázisban
     *
     * @param contactPersonDto kapcsolattartó adatai
     * @return létrehozott, vagy szerkesztett kapcsolattartó adatai
     */
    @PostMapping(value = "/contact-person")
    public ResponseEntity<ContactPersonEntity> createContactPerson(@Valid @RequestBody ContactPersonDto contactPersonDto) {
        ContactPersonEntity contactPersonEntity = contactPersonService.createContactPerson(contactPersonDto);
        return new ResponseEntity<>(contactPersonEntity, HttpStatus.OK);
    }

    /**
     * ID alapján szűrt kapcsolattartó törlése(status = deleted)
     *
     * @param id Kapcsolattartó azonosítója
     * @return törölt kapcsolattartó adatai
     */
    @DeleteMapping(value = "/contact-person/{id}")
    public ResponseEntity<ContactPersonEntity> deleteContactPersonById(@PathVariable(name = "id") int id) {
        ContactPersonEntity deletedPerson = contactPersonService.deleteContactPersonBy(id);
        return new ResponseEntity<>(deletedPerson, HttpStatus.ACCEPTED);
    }

}