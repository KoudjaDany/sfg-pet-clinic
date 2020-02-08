package training.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Specialty;
import training.springframework.sfgpetclinic.repositories.SpecialityRepository;
import training.springframework.sfgpetclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Set;


@Service
@Profile("sprindatajpa")
public class SpecialitySpringDataJpaService implements SpecialityService {

    private SpecialityRepository specialityRepository;

    public SpecialitySpringDataJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }


    @Override
    public Specialty findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty entity) {
        return specialityRepository.save(entity);
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialityRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public void delete(Specialty entity) {
        specialityRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
