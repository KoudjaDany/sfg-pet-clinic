package training.springframework.sfgpetclinic.services;

import training.springframework.sfgpetclinic.model.Owner;

import java.util.Set;


public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);
    Set<Owner> findByLastNameLike(String lastName);

}
