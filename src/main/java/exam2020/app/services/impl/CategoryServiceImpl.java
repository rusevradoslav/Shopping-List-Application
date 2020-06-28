package exam2020.app.services.impl;

import exam2020.app.models.entity.Category;
import exam2020.app.models.entity.CategoryName;
import exam2020.app.models.service.CategoryServiceModel;
import exam2020.app.repositories.CategoryRepository;
import exam2020.app.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void seedCategoriesInDB() {
        if (categoryRepository.count() == 0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName ->
                            this.categoryRepository.saveAndFlush(new Category(categoryName, String.format("Description for %s:",categoryName.name()))));
        }
    }

    @Override
    public CategoryServiceModel findCategoryByName(CategoryName name) {
        return this.categoryRepository.findFirstByCategory(name).map(category -> this.modelMapper.map(category,CategoryServiceModel.class)).orElse(null);
    }
}
