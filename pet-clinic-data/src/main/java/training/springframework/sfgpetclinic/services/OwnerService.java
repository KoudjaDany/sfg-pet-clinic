package training.springframework.sfgpetclinic.services;

import training.springframework.sfgpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);

}
