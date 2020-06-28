package exam2020.app.repositories;


import exam2020.app.models.entity.CategoryName;
import exam2020.app.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findAllByCategory(CategoryName category);
    Optional<Product> findFirstByName(String name);
}
