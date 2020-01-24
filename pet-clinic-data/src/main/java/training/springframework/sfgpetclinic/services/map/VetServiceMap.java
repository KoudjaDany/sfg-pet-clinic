package training.springframework.sfgpetclinic.services.map;

import training.springframework.sfgpetclinic.model.Vet;
import training.springframework.sfgpetclinic.services.VetService;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
    @Override
    public Vet save(Vet entity) {
        return super.save(entity.getId(),entity);
    }
}
