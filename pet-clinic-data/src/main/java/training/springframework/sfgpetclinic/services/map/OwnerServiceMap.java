package training.springframework.sfgpetclinic.services.map;

import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {


    @Override
    public Owner save(Owner entity) {
        return super.save(entity.getId(),entity);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
