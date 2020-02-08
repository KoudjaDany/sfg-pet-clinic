package training.springframework.sfgpetclinic.services.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.repositories.OwnerRepository;
import training.springframework.sfgpetclinic.services.OwnerService;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerSpringDataJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElseThrow(()-> new EntityNotFoundException("No owner of lastName "+ lastName+ " found."));
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No owner of id "+ id+ " found."));
    }

    @Override
    public Owner save(Owner entity) {
        return ownerRepository.save(entity);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void delete(Owner entity) {
        ownerRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
