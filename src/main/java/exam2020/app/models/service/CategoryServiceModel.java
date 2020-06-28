package exam2020.app.models.service;

import exam2020.app.models.entity.CategoryName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryServiceModel extends BaseServiceModel {

    private CategoryName categoryName;
    private String description;
}
