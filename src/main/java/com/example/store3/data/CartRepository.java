package com.example.store3.data;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Repository
@Getter
public class CartRepository {
//    HashMap<String, Item> itemsInCart = new HashMap<>();
    HashMap<Item, Integer> itemsInCart = new HashMap<>();
    List<HashMap<Item, Integer>> listOfOrders = new LinkedList<>();
    public CartRepository(){

    }

    public HashMap<Item, Integer> getCart(){
        return itemsInCart;
    }

    public void addToCart(Item product){
        if(itemsInCart.containsKey(product)){
            itemsInCart.put(product, itemsInCart.get(product)+1);
        }
        else{
            itemsInCart.put(product, 1);
        }
    }

    public void removeFromCart(Item product){
        if(itemsInCart.containsKey( product )){
            if( itemsInCart.get( product ) == 1 )
                itemsInCart.remove( product );
            else
                itemsInCart.put( product, itemsInCart.get( product ) - 1 );
        }
    }

    public void newCart(){
        listOfOrders.add((HashMap<Item, Integer>) itemsInCart.clone());

        itemsInCart = new HashMap<>();
    }

    public List<HashMap<Item, Integer>> getOldCarts(){
        return listOfOrders;
    }
}
