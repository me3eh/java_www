package com.example.store3.controllers;

import com.example.store3.data.Item;
import com.example.store3.data.ItemForForm;
import com.example.store3.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository items;

    public ItemController() {

    }

    @GetMapping("/")
    public String getItems(Model model) {
        model.addAttribute("items", items.getItems());
        model.addAttribute("itemForForm", new ItemForForm());
        model.addAttribute("categories", items.getCategories());
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
    @PostMapping("/addItem")
    public String addItem(@ModelAttribute ItemForForm itemForForm, Model model) {
        Item item =
            new Item(itemForForm.getName(), itemForForm.getPrice(), itemForForm.getCategoryName() );
        items.addItem( item );
        model.addAttribute("items", items.getItems());

        return "redirect:/items/";
    }
    @DeleteMapping(value = "/deleteItem/{id}")
    public String deletePost(@PathVariable("id") int id) {
        items.removeItem(id);
        return "redirect:/items/";
    }

}
