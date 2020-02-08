package training.springframework.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Specialty;
import training.springframework.sfgpetclinic.model.Vet;
import training.springframework.sfgpetclinic.services.SpecialityService;
import training.springframework.sfgpetclinic.services.VetService;

import java.util.Objects;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    private SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet entity) {
        if (Objects.nonNull(entity)){
            if (entity.getSpecialties().size() > 0){
                entity.getSpecialties().forEach(specialty -> {
                    if (specialty.getId() == null){
                        Specialty savedSpeciality = specialityService.save(specialty);
                        specialty.setId(savedSpeciality.getId());
                    }
                });
            }
            return super.save(entity);
        }else {
            return null;
        }
    }
}
