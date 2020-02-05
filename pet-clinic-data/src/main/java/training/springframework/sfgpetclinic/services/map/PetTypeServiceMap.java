package training.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.PetType;
import training.springframework.sfgpetclinic.services.PetTypeService;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

}
