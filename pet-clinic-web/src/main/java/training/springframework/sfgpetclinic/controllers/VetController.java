package training.springframework.sfgpetclinic.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {

    @RequestMapping({"/index", "/index.html", "/",""})
    public String index(Model model){

        return "vets/index";
    }
}
