package com.example.store3.controllers;

import com.example.store3.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository items;

    @Autowired
    CartRepository cartItems;
    public ItemController() {

    }

    @GetMapping("/")
    public String getItems(Model model) {
        model.addAttribute("items", items.getItems());
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
        items.addItem( item );
        model.addAttribute("items", items.getItems());

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
