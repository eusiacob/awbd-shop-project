package com.unibuc.awbd.controller;

import com.unibuc.awbd.model.*;
import com.unibuc.awbd.repository.FavoriteRepository;
import com.unibuc.awbd.repository.ProductRepository;
import com.unibuc.awbd.repository.UserRepository;
import com.unibuc.awbd.service.CategoryService;
import com.unibuc.awbd.service.DistributorService;
import com.unibuc.awbd.service.FavoriteService;
import com.unibuc.awbd.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DistributorService distributorService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Rute pentru a adăuga un produs (posibil doar admin)
    @Secured("ROLE_ADMIN")  // Verificăm că utilizatorul are rolul ROLE_ADMIN
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Distributor> distributors = distributorService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        model.addAttribute("distributors", distributors);
        return "add";  // View pentru a adăuga un produs
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public String addProduct(Model model, @Valid @ModelAttribute Product product,
                             BindingResult bindingResult,
                             @RequestParam(required = false) List<Long> distributors) {

        if (distributors == null || distributors.isEmpty()) {
            bindingResult.rejectValue("distributors", "error.distributors", "Please select at least one distributor!");
        }
        List<Category> categories = categoryService.findAll();
        List<Distributor> distributorsForSave = distributorService.findAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categories);
            model.addAttribute("distributors", distributorsForSave);
            return "add";
        }

        List<Distributor> distributorEntities = distributorService.getDistributorsByIds(distributors);
        product.setDistributors(distributorEntities);
        productService.saveProductWithDistributors(product);
        return "redirect:/products";  // Redirecționează către lista de produse
    }

    // Rute pentru a edita un produs (posibil doar admin)
    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Category> categories = categoryService.findAll();
        List<Distributor> distributors = distributorService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("distributors", distributors);
        return "edit";  // View pentru a edita un produs
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable Long id, @Valid @ModelAttribute Product product,
                              BindingResult bindingResult,
                              @RequestParam(required = false) List<Long> distributors) {
        if (distributors == null || distributors.isEmpty()) {
            bindingResult.rejectValue("distributors", "error.distributors", "Please select at least one distributor!");
        }
        List<Category> categories = categoryService.findAll();
        List<Distributor> distributorsForSave = distributorService.findAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categories);
            model.addAttribute("distributors", distributorsForSave);
            return "edit";
        }
        product.setId(id);  // Asigură-te că ID-ul este setat corect
        List<Distributor> distributorEntities = distributorService.getDistributorsByIds(distributors);
        product.setDistributors(distributorEntities);
        productService.saveProductWithDistributors(product);
        return "redirect:/products";  // Redirecționează către lista de produse
    }

    // Rute pentru a șterge un produs (posibil doar admin)
    @Secured("ROLE_ADMIN")
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";  // Redirecționează către lista de produse
    }

    @GetMapping("/favorites")
    public String showFavorites(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Favorite> favoriteProducts = favoriteRepository.findByUser(user);
        model.addAttribute("favorites", favoriteProducts);
        return "favorites";
    }

    @PostMapping("/add-to-favorites/{id}")
    public String addToFavorites(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
        Product product = productRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        boolean added = favoriteService.addToFavorites(user, product);
        if (added) {
            redirectAttributes.addFlashAttribute("successMessage", "Produsul a fost adăugat la favorite!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Produsul este deja în favorite!");
        }

        return "redirect:/products";
    }

    @PostMapping("/favorites/remove-from-favorites/{id}")
    public String removeFromFavorites(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
        Product product = productRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        favoriteService.removeFromFavorites(user, product);
        redirectAttributes.addFlashAttribute("successMessage", "Produsul a fost eliminat din favorite.");

        return "redirect:/products/favorites";
    }
}
