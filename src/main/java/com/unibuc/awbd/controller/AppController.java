package com.unibuc.awbd.controller;

import com.unibuc.awbd.dto.LoginForm;
import com.unibuc.awbd.dto.RegistrationForm;
import com.unibuc.awbd.model.Product;
import com.unibuc.awbd.repository.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class AppController {

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("loginForm") LoginForm loginForm,
            BindingResult result,
            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return "login";
        }

        try {
            request.login(loginForm.getUsername(), loginForm.getPassword());
        } catch (ServletException e) {
            result.reject("loginError", "Invalid username or password");
            return "login";
        }

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("registerForm") RegistrationForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userDetailsManager.userExists(form.getUsername())) {
            result.rejectValue("username", null, "Username already exists!");
            return "register";
        }

        userDetailsManager.createUser(
                org.springframework.security.core.userdetails.User
                        .withUsername(form.getUsername())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .roles("USER")
                        .build()
        );

        return "redirect:/login?registerSuccess";
    }

    @GetMapping("/products")
    public String listProducts(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "4") int size,
            Authentication authentication
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("pageSize", size);

        model.addAttribute("role", authentication.getAuthorities().toString());

        return "products";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
