package training.springframework.sfgpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.services.OwnerService;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    @Value("no_result")
    private String emptyListMessage;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/index", "/index.html", "/", ""})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @RequestMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject("owner", ownerService.findById(ownerId));
        return modelAndView;
    }

    @RequestMapping("/search")
    public String processFindForm(@RequestParam(value = "lastName", defaultValue = "") String name, Owner owner, BindingResult result, Model model) {
        boolean isEmptyList = false;

        Set<Owner> owners = ownerService.findByLastNameLike(name);
        if (owners.isEmpty()) {
           isEmptyList = true;
           result.rejectValue("lastName", "no_result", emptyListMessage);
           return "owners/findOwners";
        }
        if (owners.size() == 1) {
            Owner owner1 = owners.iterator().next();
            return "redirect:/owners/"+owner1.getId();
        }
        model.addAttribute("emptyList", isEmptyList);
        model.addAttribute("owners", owners);
        return "owners/ownersList";
    }

    // Both methods must have the same URI with just a difference on the HTTP Method used.
    @RequestMapping("/create")
    public String initCreateForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/create")
    public String processCreateForm(@Valid Owner owner, BindingResult result){
        if (result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }
        Owner savedOwner = ownerService.save(owner);
        log.debug("The owner of id: {} has been successfully created!", savedOwner.getId());
        return "redirect:/owners/"+ savedOwner.getId();
    }


    // Both methods must have the same URI with just a difference on the HTTP Method used.
    @RequestMapping("/update/{ownerId}")
    public String initUpdateForm(@PathVariable Long ownerId, Model model){
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/update/{ownerId}")
    public String processUpdateForm(@Valid Owner owner, @PathVariable Long ownerId, BindingResult result){
        if (result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }
        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        log.debug("The owner of id: {} has been successfully updated!", savedOwner.getId());
        return "redirect:/owners/"+ savedOwner.getId();
    }
}
