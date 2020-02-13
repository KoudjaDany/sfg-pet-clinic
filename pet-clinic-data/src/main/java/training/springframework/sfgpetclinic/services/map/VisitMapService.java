package training.springframework.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Visit;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.PetTypeService;
import training.springframework.sfgpetclinic.services.VisitService;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService {

    private PetService petService;
    private PetTypeService petTypeService;

    public VisitMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Visit save(Visit visit) {
        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
                ||visit.getPet().getOwner().getId() == null){
            new RuntimeException("Invalid Visit creation");
        }
        return super.save(visit);
    }

}
