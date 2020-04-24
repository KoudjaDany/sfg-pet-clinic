package training.springframework.sfgpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.model.Visit;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.VisitService;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("visit")
    public Visit loadAllVisits(@PathVariable Long petId, Model model){

       Pet pet = petService.findById(petId);
       Visit visit = new Visit();
       visit.setPet(pet);
       model.addAttribute("pet", pet);
       return visit;
    }

    @GetMapping("/add")
    public String addVisit(){
        return "pets/createOrUpdateVisitForm";
    }

    @GetMapping("/update/{visitId}")
    public String updateVisit(@PathVariable Long visitId,  Model model){
        model.addAttribute("visit",visitService.findById(visitId));
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/add")
    public String saveVisit(@ModelAttribute Visit visit, @PathVariable Long ownerId, BindingResult errors){
        if (errors.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }
        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }

    @PostMapping("/update")
    public String updateVisit(@ModelAttribute Visit visit, @PathVariable Long ownerId, BindingResult errors){
        if (errors.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }
        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }
}
