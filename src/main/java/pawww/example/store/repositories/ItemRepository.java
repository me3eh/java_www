package pawww.example.store.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pawww.example.store.db.Category;
import pawww.example.store.db.Item;
import pawww.example.store.db.repositories.CategoryRepositoryDB;
import pawww.example.store.db.repositories.ItemRepositoryDB;

import java.util.List;

@Repository
public class ItemRepository {
    @Autowired
    ItemRepositoryDB itemRepositoryDB;

    @Autowired
    CategoryRepositoryDB categoryRepositoryDB;
    public Category getCategory(String name){
        return (Category) itemRepositoryDB.findByCategory_Name(name);
    }

    public void addItem(Item item){
        itemRepositoryDB.save(item);
    }

    public List<Item> getItems(){
        return itemRepositoryDB.findAll();
    }

    public void removeItem(int id){
        itemRepositoryDB.deleteById(id);
    }

    public List<Item> searchItems(String name){
        return itemRepositoryDB.findByNameContaining(name);
    }

    public List<Category> getCategories(){
        return categoryRepositoryDB.findAll();
    }

    public void addCategory(Category category){
        categoryRepositoryDB.save(category);
    }
}
