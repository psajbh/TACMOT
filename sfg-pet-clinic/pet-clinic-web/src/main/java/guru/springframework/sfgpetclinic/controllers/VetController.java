package guru.springframework.sfgpetclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;

@RequestMapping("/vets")
@Controller
public class VetController {
    
    private final VetService vetService;
    
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }
	
	@GetMapping({"","/","/index","/index.html"})
	public String listVets(Model model) {
	    model.addAttribute("vets", vetService.findAll());
		return "/vets/index";
	}
	
	//return JSON which is default.  uses jackson api to convert.
	@GetMapping("/api/vets")
	public @ResponseBody Set<Vet> getVetsJson(){
		return vetService.findAll();
	}

}
