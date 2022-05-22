package pawww.example.store.repositories;

import org.springframework.stereotype.Repository;
import pawww.example.store.db.Item;

import java.util.LinkedList;
import java.util.List;

@Repository
public class Cart {
    List<Item> items = new LinkedList<>();
    float price;

    public List<Item> getItems(){
        return items;
    }

    public String getPrices() {
        StringBuilder prices = new StringBuilder();
        for( Item item : items)
            prices.append(item.getPrice() + " ");
        return prices.toString();
    }

    public void addItem(Item item){
        items.add(item);
        price += item.getPrice();
    }

    public void buy(){
        items.clear();
        price = 0f;
    }

    public float getAmount() {
        return price;
    }

    public Item isInCart(String name){
        return items.get(0);
    }
    public void removeItem(String name){
        int tempIndex = -1;
        for(int i = 0 ; i < items.size() ; ++i )
            if(items.get(i).getName().equals(name)){
                tempIndex = i;
                break;
            }
        if(tempIndex != -1)
            items.remove(tempIndex);
    }
}
