package futureofmedia.trial.service;

import futureofmedia.trial.entity.CompanyEntity;
import futureofmedia.trial.interfaces.CompanyInterface;
import futureofmedia.trial.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Company handler service
 */
@Service
public class CompanyService implements CompanyInterface {

    private final CompanyRepository companyRepository;

    /**
     * @param companyRepository load
     */
    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * @return companies' list
     */
    public List<CompanyEntity> getCompanyEntities() {
        return companyRepository.findAll();
    }

    /**
     * @param id filter companies' list
     * @return one selected company
     */
    public CompanyEntity getCompanyById(long id) {
        return companyRepository.findAllById(id);
    }
}
