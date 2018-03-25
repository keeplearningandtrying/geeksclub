package com.sivalabs.geeksclub.web.controllers;

import com.sivalabs.geeksclub.entities.Link;
import com.sivalabs.geeksclub.entities.User;
import com.sivalabs.geeksclub.models.UserProfile;
import com.sivalabs.geeksclub.repositories.LinkRepository;
import com.sivalabs.geeksclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, LinkRepository linkRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    String register(@ModelAttribute("user") @Validated User user,
                    BindingResult errors,
                    RedirectAttributes redirectAttributes){
        if(errors.hasErrors()) {
            return "registration";
        }
        Optional<User> userWithEmail = userRepository.findByEmail(user.getEmail());
        if(userWithEmail.isPresent()){
            errors.rejectValue("email","user.email.exist",
                    "User already registered with email ${user.email}");
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration is successful");
        return "redirect:/registration_status";
    }

    @GetMapping("/users/{userId}")
    String showUserProfile(@PathVariable("userId") Long userId , Model model)  {
        User user = userRepository.findById(userId).get();
        List<Link> links = linkRepository.findByCreatedById(userId);
        UserProfile userProfile = new UserProfile(user.getId(), user.getName(), user.getEmail(), user.getWebsite(), user.getBio(), links);
        model.addAttribute("userProfile", userProfile);
        return "userprofile";
    }
}
