package training.springframework.sfgpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.model.PetType;
import training.springframework.sfgpetclinic.services.OwnerService;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.PetTypeService;
import training.springframework.sfgpetclinic.services.VisitService;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    public static final  String CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService, VisitService visitService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/create")
    public String getCreatePetForm(Owner owner, Model model){
        Pet pet = Pet.builder().build();
        owner.addPet(pet);
        model.addAttribute("pet", pet);
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/create")
    public String createPet(@Valid Pet pet, BindingResult result, Owner owner,  Model model){
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()){
            model.addAttribute("pet", pet);
            return CREATE_OR_UPDATE_PET_FORM;
        }
        petService.save(pet);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/update/{petId}")
    public String getUpdatePetForm( Owner owner, @PathVariable Long petId, Model  model){
        model.addAttribute("pet", petService.findById(petId));
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/update/{petId}")
    public String updatePet(@Valid Pet pet, Owner owner, @PathVariable Long petId, BindingResult result, Model model){
        if (result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return CREATE_OR_UPDATE_PET_FORM;
        }
        pet.setId(petId);
        petService.save(pet);
        return "redirect:/owners/{ownerId}";
    }

}
