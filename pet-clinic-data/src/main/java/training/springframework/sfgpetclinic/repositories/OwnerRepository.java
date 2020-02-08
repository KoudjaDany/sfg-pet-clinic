package training.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springframework.sfgpetclinic.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
