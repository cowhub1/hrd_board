package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.board.model.User;
import com.example.board.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession session;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/signin")
	public String signin() {
		String pwd = passwordEncoder.encode("1");

		return "signin";
	}

	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user) {
		Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
		
		boolean isPresent = dbUser.isPresent();
		if(isPresent){
			String encodedpwd = dbUser.get().getPwd();
			String userpwd = user.getPwd();
			boolean isMatch = passwordEncoder.matches(userpwd, encodedpwd);
		
		if (isMatch) {
			session.setAttribute("email", dbUser.get().getEmail());
			session.setAttribute("name", dbUser.get().getName());
			session.setAttribute("user_info", dbUser.get());
			
		}else{
			return "redirect:/signin";
		}
	}else{
			return "redirect:/signin";
		}
	return "redirect:/";
		
	}

	@GetMapping("/signout")
	public String signout() {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user) {
		String newpwd = passwordEncoder.encode(user.getPwd());
    	user.setPwd(newpwd);
		User idea = userRepository.save(user);
		System.out.println(idea);
		return "redirect:/";
	}
}