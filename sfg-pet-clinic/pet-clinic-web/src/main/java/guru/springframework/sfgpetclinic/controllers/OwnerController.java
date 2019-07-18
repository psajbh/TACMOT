package guru.springframework.sfgpetclinic.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/owners")
@Slf4j
@Controller
public class OwnerController {
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		log.info("setAllowedFields: ");
		dataBinder.setDisallowedFields("id"); // security good in that all model classes have an id field.
	}

	// @GetMapping({"","/","/index","/index.html"})
	public String listOwners(Model model) {
		log.info("listOwners: ");
		model.addAttribute("owners", ownerService.findAll());
		return "/owners/index";
	}

	@GetMapping("/find")
	// @GetMapping({"","/","/index","/index.html"})
	public String findOwners(Model model) {
		log.info("findOwners: ");
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";

	}

	// BindingResult interface that inherits from Errors
	// [[Spring - Message codes created by MessageCodesResolver and their resolution
	// to error
	// messages|https://www.logicbig.com/tutorials/spring-framework/spring-core/error-codes.html]]
	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		log.info("processFindForm: ");
		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name, if last name not entered, will get all owners.
		List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

		if (results.isEmpty()) {
			// no owners found Errors method
			// Errors.rejectValue (field, errorCode (message key), fallback default message)
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if (results.size() == 1) {
			// 1 owner found
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			// multiple owners found
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}

	@GetMapping("/new")
	public String initCreationForm(Model model) {
		log.info("initCreationForm: ");
		model.addAttribute("owner", Owner.builder().build());
		return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		log.info("processCreationForm: ");
		if (result.hasErrors()) {
			return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		log.info("initUpdateOwnerForm: ");
		model.addAttribute(ownerService.findById(ownerId));
		return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
		log.info("processUpdateOwnerForm: ");
		if (result.hasErrors()) {
			return OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{ownerId}")
	public ModelAndView displayOwner(@PathVariable("ownerId") Long ownerId) {
		log.info("displayOwner: ");
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}

}
