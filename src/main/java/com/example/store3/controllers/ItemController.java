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
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository items;

    @Autowired
    CartRepository cartItems;
    ValidatorFactory validatorFactory;

    public ItemController() {
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

    @GetMapping("/category")
    public String getItemsFromCategory(@RequestParam(value = "name", defaultValue = "snacks") String category, Model model) {
        model.addAttribute("items", this.items.getItemsFromCategory(category));
        model.addAttribute("category", category);
        return "items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", this.items.getItem(id));
        return "item";
    }

    @GetMapping("/addItem")
    public String addItemForm(Model model) {
        model.addAttribute("itemForForm", new ItemForForm());
        model.addAttribute("categories", items.getCategories());
        return "itemForm";
    }
    @PostMapping("/addItem")
    public String addItem(@ModelAttribute ItemForForm itemForForm, Model model) {
        Item item = new Item(itemForForm.getName(), itemForForm.getPrice(), itemForForm.getCategoryName() );

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Item>> constraintViolations = validator.validate(item);
        List<String> messages;
        if (constraintViolations.size() > 0) {
            messages = new LinkedList<>();
            for (ConstraintViolation<Item> violation : constraintViolations) {
                System.out.println(violation.getMessage());
                messages.add(violation.getMessage());
            }

            model.addAttribute("itemForForm", new ItemForForm());
            model.addAttribute("categories", items.getCategories());
            model.addAttribute("error", messages);
            return "itemForm";
        }

        items.addItem(item);
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
