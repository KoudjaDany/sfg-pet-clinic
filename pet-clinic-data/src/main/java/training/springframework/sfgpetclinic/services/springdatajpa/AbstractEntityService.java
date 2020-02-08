package training.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import training.springframework.sfgpetclinic.model.BaseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEntityService<E extends BaseEntity, ID extends Long>  {

    private CrudRepository<E,ID> repository;

    protected AbstractEntityService(CrudRepository<E, ID> repository) {
        this.repository = repository;
    }

    private <T extends CrudRepository>

    E findById(ID id){
       return repository.findById(id).orElseThrow(()-> new EntityNotFoundException(""));
    }

    E save(E entity){
        return repository.save(entity);
    }

    Set<E> findAll(){
        Set<E> entities = new HashSet<>();
        repository.findAll().forEach(entities::add);
        return entities;
    }

    void delete(E entity){
        repository.delete(entity);
    }

    void deleteById(ID id){
        repository.deleteById(id);
    }

}
