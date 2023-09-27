package com.storecode.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Provider;
import com.storecode.services.ProviderService;

@Controller
@RequestMapping("/providers")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	@GetMapping("/addProvider")
	public String showProviderForm(Provider provider){
		return "provider/addProvider";
	}
	
	@PostMapping("/saveProvider")
	public String saveProvider(@Valid @ModelAttribute Provider provider, BindingResult fields, Model model){
		if (fields.hasErrors()) {
			return "provider/addProvider";
		}else {
			providerService.save(provider);
			model.addAttribute("providers", providerService.getAll());
			return "redirect:/provider/listProviders";
		}
		
	}
	
	@GetMapping("/editProvider/{idProvider}")
	public String showUpdateForm(@PathVariable("idProvider") long idProvider, Model model){
		Provider provider = providerService.getById(idProvider);
		model.addAttribute("provider", provider);
		return "provider/updateProvider";
	}
	
	@PostMapping("/updateProvider/{idProvider}")
	public String updateProvider(@PathVariable("idProvider") long idProvider, Provider provider,
			BindingResult fields, Model model){
		if (fields.hasErrors()) {
			provider.setId(idProvider);
			return "provider/updateProvider";
		}else {
			providerService.save(provider);
			model.addAttribute("listProviders", providerService.getAll());
			return "redirect:/provider/listProviders";
		}
	}
	
	@GetMapping("/deleteProvider/{idProvider}")
    public String deleteProvider(@PathVariable("idProvider") long idProvider, Model model) {
        providerService.deleteById(idProvider);
        model.addAttribute("providers", providerService.getAll());
        return "redirect:/provider/listProviders";
    }
	
	@GetMapping("/listProviders")
  	public String list(Provider provider, Model model) {
  		model.addAttribute("providers", providerService.getAll());
        return "/provider/listProviders";
  	}
}
