package training.springframework.sfgpetclinic.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training.springframework.sfgpetclinic.model.Owner;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);

    @Query("SELECT DISTINCT owner from Owner owner where owner.lastName like %:lastName%")
    Set<Owner> findByLastNameLike(@Param("lastName") String lastName);
}
