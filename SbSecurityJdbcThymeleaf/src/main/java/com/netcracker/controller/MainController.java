package com.netcracker.controller;

import java.security.Principal;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.dao.AppUserDAO;
import com.netcracker.model.AppUser;
import com.netcracker.utils.ServiceMail;
import com.netcracker.utils.WebUtils;

@Controller
public class MainController {

	@Autowired
    private AppUserDAO appUserDAO;
	
    @GetMapping(value = { "/", "/welcome" })
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }
 
    @GetMapping(value = "/login")
    public String loginPage(Model model) {
 
        return "loginPage";
    }
    
    @GetMapping(value = "/registration")
    public String registrationForm(@RequestParam(value = "username", required = false) @Valid String username,
    							   @RequestParam(value = "userEmail", required = false) @Valid String userEmail,
    							   @RequestParam(value = "password", required = false) @Valid String password,
    							   Model model) throws AddressException, MessagingException {
    	String resultRegistration = "registrationForm";

    	if(username != null && userEmail != null && password != null) {
    		boolean reg = appUserDAO.createNewUser(new AppUser(username, userEmail, password));
    		if(reg) {
    			resultRegistration = "successRegistrationPage";
    			String info = "Mail sent to " + userEmail;
                model.addAttribute("info", info);
                ServiceMail mail = new ServiceMail();
                mail.send(userEmail);
    		}
    		else {
    			resultRegistration = "registrationForm";
    		}
    	}
        return resultRegistration;
    }
 
    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();
        System.out.println("User Name: " + userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }
 
    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
}
