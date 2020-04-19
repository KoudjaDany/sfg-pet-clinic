package training.springframework.sfgpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.PetType;
import training.springframework.sfgpetclinic.services.OwnerService;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.PetTypeService;
import training.springframework.sfgpetclinic.services.VisitService;

import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

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

}
