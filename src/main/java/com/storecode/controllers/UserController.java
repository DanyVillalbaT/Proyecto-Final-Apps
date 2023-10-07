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

import com.storecode.models.User;
import com.storecode.models.UserSessionSingleton;
import com.storecode.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	 	@Autowired
	    private UserService userService;
	 	
	    @GetMapping("/login")
	    public String showUserForm(Model model) {
	        model.addAttribute("user", new User());
	        return "user/login";
	    }

	    @PostMapping("/signIn/{emailUser}&{passwordUser}")
	    public String signIn(@PathVariable("emailUser") String email, @PathVariable("passwordUser") String password, Model model) {
	    	userService.getUserByEmailAndPassword(email, password);
	    	UserSessionSingleton.getINSTANCIA().writeUserSession();
	        return "redirect:/products/listProducts"; // Redirecciona al formulario después de guardar
	    }

	    @PostMapping("/saveUser")
	    public String saveUser(@ModelAttribute User user, Model model) {
	    	userService.save(user);
	        System.out.println("Usuario guardado: " + user.getName());
	        return "redirect:/users/login"; // Redirecciona al formulario después de guardar
	    }
	    
	    @GetMapping("/usersTable")
	    public String showUsersTable(Model model) {
	    	model.addAttribute("users", userService.getAll());
	        return "user/usersTable";
	    }
	    
	    @GetMapping("/deleteUser/{idUser}")
	    public String deleteUser(@PathVariable("idUser") long idUser, Model model) {
	    	User user = userService.getByiId(idUser);
	    	userService.delete(idUser);
	        model.addAttribute("users", userService.getAll());
	        return "redirect:/users/usersTable";
	    }
	    
	    @GetMapping("editUser/{idUser}")
	    public String showUpdateForm(@PathVariable("idUser") long idUser, Model model) {
	    	User user = userService.getByiId(idUser);
	        model.addAttribute("user", user);
	        return "user/updateUser";
	    }
	    
	    
	    @PostMapping("/updateUser/{idUser}")
	    public String updateProduct(@PathVariable("idUser") long idUser,  User user, BindingResult result, Model model) {
		        //productService.save(product);
		        User oldUSer = userService.getByiId(idUser);
		        
		        oldUSer.setName(user.getName());
		        oldUSer.setAddress(user.getAddress());
		        oldUSer.setEmail(user.getEmail());
		        oldUSer.setPassword(user.getPassword());
		        oldUSer.setDocument(user.getDocument());	       
		        oldUSer.setRol(user.getRol());      
		        oldUSer.setTelephone(user.getTelephone());
		        oldUSer.setZipCode(user.getZipCode());
		        
		        userService.save(oldUSer);
		        model.addAttribute("users",userService.getAll());
		        return "redirect:/users/usersTable";
	    
	    }
	    
	    
}

