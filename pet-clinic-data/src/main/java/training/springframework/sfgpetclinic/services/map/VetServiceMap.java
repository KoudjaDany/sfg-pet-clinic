package training.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Vet;
import training.springframework.sfgpetclinic.services.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

}
