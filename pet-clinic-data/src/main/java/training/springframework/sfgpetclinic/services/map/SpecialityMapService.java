package training.springframework.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Specialty;
import training.springframework.sfgpetclinic.services.SpecialityService;

@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Specialty,Long> implements SpecialityService {

}
