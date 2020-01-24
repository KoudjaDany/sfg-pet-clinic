package training.springframework.sfgpetclinic.services.map;

import training.springframework.sfgpetclinic.model.Vet;
import training.springframework.sfgpetclinic.services.CrudService;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements CrudService<Vet,Long> {
    @Override
    public Vet save(Vet entity) {
        return super.save(entity.getId(),entity);
    }
}
