package training.springframework.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.services.OwnerService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

}
