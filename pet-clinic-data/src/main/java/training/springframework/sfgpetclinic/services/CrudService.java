package training.springframework.sfgpetclinic.services;

import java.util.Set;

/**
 *
 * @param <E> the Entity type to manage
 */
public interface CrudService<E> {
    E findById(Long id);

    E save(E entity);

    Set<E> findAll();
}
