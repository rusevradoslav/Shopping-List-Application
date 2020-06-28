package exam2020.app.services.impl;

import exam2020.app.models.entity.Product;
import exam2020.app.models.service.CategoryServiceModel;
import exam2020.app.models.service.ProductServiceModel;
import exam2020.app.models.view.ProductViewModel;
import exam2020.app.repositories.ProductRepository;
import exam2020.app.services.CategoryService;
import exam2020.app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        System.out.println();
        CategoryServiceModel categoryServiceModel = this.categoryService.findCategoryByName(productServiceModel.getCategory().getCategoryName());
        productServiceModel.setCategory(categoryServiceModel);
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        this.productRepository.saveAndFlush(product);

    }

    @Override
    public List<ProductViewModel> getAllFoodProducts() {
        System.out.println();
        return this.productRepository.findAll().stream().filter(product -> product.getCategory().getCategory().name().equals("FOOD"))
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);
                    return productViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> getAllDrinkProducts() {
        return this.productRepository.findAll().stream().filter(product -> product.getCategory().getCategory().name().equals("DRINK"))
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);
                    return productViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> getAllHouseHoldProducts() {
        return this.productRepository.findAll().stream().filter(product -> product.getCategory().getCategory().name().equals("HOUSEHOLD"))
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);
                    return productViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> getAllOtherProducts() {
        return this.productRepository.findAll().stream().filter(product -> product.getCategory().getCategory().name().equals("OTHER"))
                .map(product -> {
                    ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);
                    return productViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public double getSumOfALLProducts() {
        return this.productRepository.findAll().stream().mapToDouble(product -> product.getPrice().doubleValue()).sum();
    }

    @Override
    public void buyProduct(String id) {
        Product product = this.productRepository.findById(id).orElse(null);
        this.productRepository.delete(product);
    }

    @Override
    public void buyAll() {
        this.productRepository.findAll().stream().forEach(product -> this.productRepository.delete(product));
    }

    @Override
    public ProductServiceModel findByName(String name) {
        return this.productRepository.findFirstByName(name).map(product -> modelMapper.map(product,ProductServiceModel.class)).orElse(null);
    }
}
