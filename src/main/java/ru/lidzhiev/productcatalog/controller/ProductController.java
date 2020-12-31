package ru.lidzhiev.productcatalog.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateProcessingException;
import ru.lidzhiev.productcatalog.entity.Product;
import ru.lidzhiev.productcatalog.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("products", repository.findAll());
        return "index";
    }

    @GetMapping("/addingForm")
    public String addingForm(Product product) {
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(Product product, Model model) {
        repository.save(product);
        model.addAttribute("products", repository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, Model model) {
        repository.deleteById(id);
        model.addAttribute("products", repository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, Product product, Model model) {
        repository.save(product);
        model.addAttribute("products", repository.findAll());
        return "index";
    }

    @GetMapping("getProduct")
    public String getProduct(@RequestParam String name, Model model) {
        Product product;
        try {
            product = repository.findByName(name);
        } catch (TemplateProcessingException e) {
            product = null;
        }
        model.addAttribute("product", product);
        model.addAttribute("name", name);
        return "get-product";
    }
}
