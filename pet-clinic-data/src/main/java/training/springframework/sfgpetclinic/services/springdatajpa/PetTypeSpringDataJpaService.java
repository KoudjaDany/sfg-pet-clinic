package training.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.PetType;
import training.springframework.sfgpetclinic.repositories.PetTypeRepository;
import training.springframework.sfgpetclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;


@Service
@Profile("sprindatajpa")
public class PetTypeSpringDataJpaService implements PetTypeService {

    private PetTypeRepository petTypeRepository;

    public PetTypeSpringDataJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType entity) {
        return petTypeRepository.save(entity);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public void delete(PetType entity) {
        petTypeRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
