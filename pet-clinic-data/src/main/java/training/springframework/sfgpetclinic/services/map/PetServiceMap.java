package training.springframework.sfgpetclinic.services.map;

import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.services.PetService;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {
    @Override
    public Pet save(Pet entity) {
        return super.save(entity.getId(),entity);
    }
}
