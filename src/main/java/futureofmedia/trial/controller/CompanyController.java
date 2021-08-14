package futureofmedia.trial.controller;

import futureofmedia.trial.entity.CompanyEntity;
import futureofmedia.trial.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Company handler
 */
@RestController
@RequestMapping("api")
public class CompanyController {

    /**
     * Company service to managing
     */
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * @return get all Companies from database
     */
    @GetMapping(value = "/company")
    public ResponseEntity<List<CompanyEntity>> getCompanyEntities() {
        List<CompanyEntity> companyEntities = companyService.getCompanyEntities();
        return new ResponseEntity<>(companyEntities, HttpStatus.OK);
    }

    /**
     * @param id filter companies' list
     * @return one selected company
     */
    @GetMapping(value = "/company/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable(name = "id") long id) {
        CompanyEntity companyEntities = companyService.getCompanyById(id);
        return new ResponseEntity<>(companyEntities, HttpStatus.OK);
    }
}