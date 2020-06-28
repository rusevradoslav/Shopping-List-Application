package exam2020.app.services;

import exam2020.app.models.service.ProductServiceModel;
import exam2020.app.models.service.UserServiceModel;
import exam2020.app.models.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    void addProduct(ProductServiceModel map);

    List<ProductViewModel> getAllFoodProducts();

    List<ProductViewModel> getAllDrinkProducts();

    List<ProductViewModel> getAllHouseHoldProducts();

    List<ProductViewModel> getAllOtherProducts();

    double getSumOfALLProducts();

    void buyProduct(String id);

    void buyAll();

    ProductServiceModel findByName(String name);
}
