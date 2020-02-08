package training.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springframework.sfgpetclinic.model.Visit;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
