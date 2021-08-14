package futureofmedia.trial.repository;

import futureofmedia.trial.entity.ContactPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ContactPerson tábla lekérései
 */
@Repository
public interface ContactPersonRepository extends JpaRepository<ContactPersonEntity, Integer> {
    ContactPersonEntity findAllById(int id);

    @Query("SELECT c from ContactPersonEntity c where c.status = 'active' " +
            "AND" +
            " (:firstName is null or c.firstName = :firstName) " +
            "AND" +
            " (:lastName is null or c.lastName = :lastName) " +
            "AND " +
            " (:email is null or c.email = :email) " +
            "AND" +
            " (:phoneNumber is null or c.phoneNumber = :phoneNumber) " +
            "AND" +
            " (:description is null or c.description = :description)")
    Page<ContactPersonEntity> findAllIsActive(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber,
            @Param("description") String description,
            Pageable pageable);
}