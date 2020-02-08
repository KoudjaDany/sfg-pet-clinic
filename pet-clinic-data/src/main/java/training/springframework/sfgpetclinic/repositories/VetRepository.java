package training.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springframework.sfgpetclinic.model.Vet;

@Repository
public interface VetRepository extends CrudRepository<Vet, Long> {
}
