package pawww.example.store.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawww.example.store.db.Category;
import pawww.example.store.db.Item;

import java.util.List;

public interface CategoryRepositoryDB extends JpaRepository<Category, Integer> {
    List<Item> findCategoryByName(String name);

}