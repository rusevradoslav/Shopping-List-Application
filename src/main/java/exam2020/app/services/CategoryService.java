package exam2020.app.services;

import exam2020.app.models.entity.CategoryName;
import exam2020.app.models.service.CategoryServiceModel;
import exam2020.app.models.service.UserServiceModel;

public interface CategoryService {


     void seedCategoriesInDB();

    CategoryServiceModel findCategoryByName(CategoryName name);
}
