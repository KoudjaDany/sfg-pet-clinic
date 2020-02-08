package training.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springframework.sfgpetclinic.model.Specialty;

@Repository
public interface SpecialityRepository extends CrudRepository<Specialty, Long> {
}
