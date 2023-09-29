package com.storecode.controllers;

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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/providers")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	@GetMapping("/addProvider")
	public String showProviderForm(Model model){
		model.addAttribute("provider", new Provider());
		return "provider/addProvider";
	}
	
	@PostMapping("/saveProvider")
	public String saveProvider(@ModelAttribute("provider") @Valid Provider provider, BindingResult fields, Model model){
		if (fields.hasErrors()) {
			return "provider/addProvider";
		}else {
			providerService.save(provider);
			System.out.println("El proveedor fue registrado correctamente: " + provider.getName());
			model.addAttribute("providers", providerService.getAll());
			return "redirect:/providers/listProviders";
		}
		
	}
	
	@GetMapping("/editProvider/{idProvider}")
	public String showUpdateForm(@PathVariable("idProvider") long idProvider, Model model){
		Provider provider = providerService.getById(idProvider);
		model.addAttribute("provider", provider);
		return "provider/updateProvider";
	}
	
	@PostMapping("/updateProvider/{idProvider}")
	public String updateProvider(@PathVariable("idProvider") long idProvider,
			@ModelAttribute("provider") @Valid Provider provider, BindingResult fields, Model model){
		if (fields.hasErrors()) {
			provider.setId(idProvider);
			return "provider/updateProvider";
		}else {
			Provider providerAux = providerService.getById(idProvider);
			providerAux.setName(provider.getName());
			providerAux.setUbication(provider.getUbication());
			providerAux.setPhone(provider.getPhone());
			providerService.save(providerAux);
			model.addAttribute("listProviders", providerService.getAll());
			return "redirect:/providers/listProviders";
		}
	}
	
	@GetMapping("/deleteProvider/{idProvider}")
    public String deleteProvider(@PathVariable("idProvider") long idProvider, Model model) {
		Provider provider = providerService.getById(idProvider);
		if(providerService.existsProductByProvider(provider)){
			model.addAttribute("error", "El proveedor no puede ser eliminado porque se encuentra con productos a cargo");
			return "redirect:/providers/listProviders";
		}else {
			providerService.deleteById(idProvider);
	        model.addAttribute("providers", providerService.getAll());
	        return "redirect:/providers/listProviders";
		}
    }
	
	@GetMapping("/listProviders")
  	public String list(Provider provider, Model model) {
  		model.addAttribute("providers", providerService.getAll());
        return "/provider/listProviders";
  	}
}
