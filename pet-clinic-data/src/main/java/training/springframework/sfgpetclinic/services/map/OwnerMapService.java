package training.springframework.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.services.OwnerService;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.PetTypeService;

import java.util.Objects;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {

    private PetService petService;
    private PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner save(Owner entity) {
        if (Objects.nonNull(entity)){
            if (Objects.nonNull(entity.getPets()) && entity.getPets().size() > 0){
                entity.getPets().forEach(pet -> {
                    if (pet.getPetType() != null){
                        if (pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("A pet type is required");
                    }
                    if (pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(entity);
        }else {
            return null;
        }

    }

    @Override
    public Owner findByLastName(String lastName) {
        return findAll()
                .stream()
                .filter(owner -> lastName.equalsIgnoreCase(owner.getLastName()))
                .findFirst()
                .orElse(null);
    }

}
