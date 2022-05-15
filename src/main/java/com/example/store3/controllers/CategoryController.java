package com.example.store3.controllers;

import com.example.store3.data.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ItemRepository items;

    @Autowired
    CartRepository cartItems;
    ValidatorFactory validatorFactory;

    public CategoryController() {
        validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
    }

    @GetMapping("/")
    public String getItems(Model model) {
        model.addAttribute("items", items.getItems());
        List p = new LinkedList<String>();
        p.add("Sa");
        p.add("Sa3");

        model.addAttribute("error", p);

        return "items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", this.items.getItem(id));
        return "item";
    }

    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("itemCategoryForForm", new ItemCategoryForForm());
        return "categoryForm";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute ItemCategoryForForm categoryForForm, Model model) {
        System.out.println(categoryForForm.getName());
        ItemCategory itemCategory = new ItemCategory(categoryForForm.getName());

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<ItemCategory>> constraintViolations = validator.validate(itemCategory);
        List<String> messages;
        if (constraintViolations.size() > 0) {
            messages = new LinkedList<>();
            for (ConstraintViolation<ItemCategory> violation : constraintViolations) {
                System.out.println(violation.getMessage());
                messages.add(violation.getMessage());
            }
            model.addAttribute("itemForForm", new ItemCategoryForForm());
            model.addAttribute("categories", items.getCategories());
            model.addAttribute("error", messages);
            return "categoryForm";
        }

        items.addCategory(itemCategory);
        System.out.println("Valid Object");
        return "redirect:/items/";

    }
    @DeleteMapping(value = "/deleteItem/{id}")
    public String deletePost(@PathVariable("id") int id) {
        Item item = items.getItem(id);
        if(item == null)
            return "redirect:/items/";
        cartItems.removeFromCart(item);

        items.removeItem(id);
        return "redirect:/items/";
    }

}
