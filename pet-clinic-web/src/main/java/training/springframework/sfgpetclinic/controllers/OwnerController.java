package training.springframework.sfgpetclinic.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.services.OwnerService;

import java.util.Set;

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
}
