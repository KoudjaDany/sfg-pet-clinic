package training.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.services.PetService;

@Service
public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {

}
