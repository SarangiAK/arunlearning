package com.curewell.invoicemanagement.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.curewell.invoicemanagement.entities.User;
import com.curewell.invoicemanagement.entities.repository.UserDAO;
import com.curewell.invoicemanagement.security.SecurityService;
import com.curewell.invoicemanagement.vo.CurewellInvoiceOrderVO;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserDAO userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/curewell/showReg")
	public String showRegistrationPage() {
		return "registerUser";

	}

	@PostMapping("curewell/registerUser")
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "login";
	}

	@GetMapping("/curewell/")
	public String showLoginPage(Model loginModel) {
		User user = new User();
		loginModel.addAttribute("user", user);
		
		return "login";
	}

	@PostMapping("/curewell/login")
	public String login(String email, String password) {
		boolean loginResponse = securityService.login(email, password);
		if (loginResponse) {
			return "index";
		}
		return "login";
	}
}
