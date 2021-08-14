package futureofmedia.trial.interfaces;

import futureofmedia.trial.entity.CompanyEntity;

import java.util.List;

public interface CompanyInterface {
    List<CompanyEntity> getCompanyEntities();

    CompanyEntity getCompanyById(long id);
}
