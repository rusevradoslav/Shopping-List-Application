package exam2020.app.repositories;

import exam2020.app.models.entity.Category;
import exam2020.app.models.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findFirstByCategory(CategoryName name);
}
