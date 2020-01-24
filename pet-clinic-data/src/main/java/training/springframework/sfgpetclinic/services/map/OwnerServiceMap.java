package training.springframework.sfgpetclinic.services.map;

import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements CrudService<Owner,Long> {


    @Override
    public Owner save(Owner entity) {
        return super.save(entity.getId(),entity);
    }

}
