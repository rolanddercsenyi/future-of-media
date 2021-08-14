package futureofmedia.trial.repository;

import futureofmedia.trial.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * company tábla lekérdezései
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    CompanyEntity findAllById(long id);
}
