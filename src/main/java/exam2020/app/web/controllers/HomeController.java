package exam2020.app.web.controllers;

import exam2020.app.models.view.ProductViewModel;
import exam2020.app.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {

            List<ProductViewModel> foods = this.productService.getAllFoodProducts();
            List<ProductViewModel> drinks = this.productService.getAllDrinkProducts();
            List<ProductViewModel> households = this.productService.getAllHouseHoldProducts();
            List<ProductViewModel> others = this.productService.getAllOtherProducts();
            double sumAllProducts = this.productService.getSumOfALLProducts();
            modelAndView.addObject("foods",foods);
            modelAndView.addObject("drinks",drinks);
            modelAndView.addObject("households",households);
            modelAndView.addObject("others",others);
            modelAndView.addObject("sumAllProducts",sumAllProducts);
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

}
