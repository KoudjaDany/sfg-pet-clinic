package training.springframework.sfgpetclinic.services;

import java.util.Set;

/**
 *
 * @param <E> the Entity type to manage
 */
public interface CrudService<E, ID> {
    E findById(ID id);

    E save(E entity);

    Set<E> findAll();

    void delete(E entity);

    void deleteById(ID id);
}
