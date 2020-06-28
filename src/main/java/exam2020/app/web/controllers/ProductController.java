package exam2020.app.web.controllers;

import exam2020.app.models.binding.ProductAddBindingModel;
import exam2020.app.models.service.ProductServiceModel;
import exam2020.app.models.service.UserServiceModel;
import exam2020.app.services.ProductService;
import exam2020.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, UserService userService, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    private String addProduct(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    private String addProductConfirm(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:/products/add";
        }
        ProductServiceModel product = this.productService.findByName(productAddBindingModel.getName());
        if (product == null) {
            this.productService.addProduct(modelMapper.map(productAddBindingModel, ProductServiceModel.class));
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("alreadyExist", true);
            return "redirect:/products/add";
        }


    }

    @GetMapping("/buy/{id}")
    private String buyProduct(@PathVariable("id") String id, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        this.productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buyAll")
    private String buyAllProduct(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        this.productService.buyAll();
        return "redirect:/";
    }

}
