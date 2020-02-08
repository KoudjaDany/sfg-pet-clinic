package training.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Visit;
import training.springframework.sfgpetclinic.repositories.VisitRepository;
import training.springframework.sfgpetclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSpringDataJpaService implements VisitService {

    private VisitRepository visitRepository;

    public VisitSpringDataJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit entity) {
        return visitRepository.save(entity);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void delete(Visit entity) {
        visitRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id);
    }
}
