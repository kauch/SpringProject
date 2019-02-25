package com.netcracker.controller;

import java.security.Principal;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netcracker.dao.AppOrderDao;
import com.netcracker.dao.AppRoleDAO;
import com.netcracker.dao.AppUserDAO;
import com.netcracker.model.AppOrder;
import com.netcracker.model.AppUser;
import com.netcracker.utils.ServiceMail;
import com.netcracker.utils.WebUtils;

@Controller
public class MainController {

	@Autowired
    private AppUserDAO appUserDAO;
	
	@Autowired
    private AppRoleDAO appRoleDAO;
	
	@Autowired
	private AppOrderDao appOrderDao;
	
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
    public String registrationForm(@RequestParam(value = "username", required = false) String username,
    							   @RequestParam(value = "userEmail", required = false) String userEmail,
    							   @RequestParam(value = "password", required = false) String password,
    							   Model model) throws MessagingException {
    	String resultRegistration = "registrationForm";

    	if(username != null && userEmail != null && password != null) {
    		boolean reg = appUserDAO.createNewUser(new AppUser(username, userEmail, password));
    		if(reg) {
    			resultRegistration = "successRegistrationPage";
    			String info = "Mail sent to " + userEmail;
                model.addAttribute("info", info);
                ServiceMail mail = new ServiceMail();
                AppUser user = appUserDAO.findUserAccount(username);
                appRoleDAO.addRolesForUser(user.getUserId(), 2L);
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
    
    @GetMapping(value = "/createOrder")
    public String createOrderPage(@RequestParam(value = "OrderID", required = false) String orderID,
			   					  @RequestParam(value = "OrderWeight", required = false) String orderWeight,
			                      @RequestParam(value = "Destination", required = false) String destination,
			                      Model model, Principal principal) {
    	String userName = principal.getName();
    	AppUser user = appUserDAO.findUserAccount(userName);
    	if(orderID != null && orderWeight != null && destination != null) {
	    	Long id = Long.parseLong(orderID);
	    	int weight = Integer.parseInt(orderWeight);
	    	appOrderDao.createOrder(new AppOrder(user.getUserId(), id, weight, destination));
    	}
        return "createOrderPage";
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
