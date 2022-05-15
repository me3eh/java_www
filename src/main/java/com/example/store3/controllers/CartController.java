package com.example.store3.controllers;

import com.example.store3.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartRepository cartItems;

    @Autowired
    ItemRepository items;

    public CartController() {

    }

    @GetMapping("/")
    public String getItems(Model model) {
        HashMap<Item, Integer> items= cartItems.getItemsInCart();
        Float priceForCart = 0f;
        for(Map.Entry<Item, Integer> entry : items.entrySet()){
            priceForCart += entry.getKey().getPrice() * entry.getValue();
        }
        model.addAttribute("cartItems", cartItems.getItemsInCart());
        model.addAttribute("cartPrice", priceForCart);

        return "cart";
    }
    @PostMapping("/newCart")
    public String newCart(Model model) {
        cartItems.newCart();

        model.addAttribute("cartItems", cartItems.getItemsInCart());
        return "redirect:/cart/";
    }
    @GetMapping("/allCarts")
    public String allCarts(Model model) {
        model.addAttribute("oldCarts", cartItems.getOldCarts());
        return "allCarts";
    }
    @PostMapping(value = "/addToCart/{id}")
    public String post(@PathVariable("id") int id, Model model) {
        Item p = items.getItem(id);
        cartItems.addToCart(p);
        model.addAttribute("cartItems", cartItems.getItemsInCart());
        model.addAttribute("oldCarts", cartItems.getOldCarts());

        return "redirect:/cart/";
    }

}
