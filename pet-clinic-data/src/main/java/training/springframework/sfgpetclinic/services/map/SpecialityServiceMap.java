package training.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Specialty;
import training.springframework.sfgpetclinic.services.SpecialityService;

@Service
public class SpecialityServiceMap extends AbstractMapService<Specialty,Long> implements SpecialityService {

}
