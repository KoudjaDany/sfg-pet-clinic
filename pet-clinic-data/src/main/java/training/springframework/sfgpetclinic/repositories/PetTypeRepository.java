package training.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springframework.sfgpetclinic.model.PetType;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
