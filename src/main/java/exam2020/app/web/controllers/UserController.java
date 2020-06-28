package exam2020.app.web.controllers;

import exam2020.app.models.binding.UserLoginBindingModel;
import exam2020.app.models.binding.UserRegisterBindingModel;
import exam2020.app.models.service.UserServiceModel;
import exam2020.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(Model model) {

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }


    @PostMapping("login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        } else {
            UserServiceModel user = this.userService.findUserByUsername(userLoginBindingModel.getUsername());
            if (user != null && userLoginBindingModel.getPassword().equals(user.getPassword())) {
                httpSession.setAttribute("user", user);
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("notFound", true);
                return "redirect:/users/login";
            }
        }

    }


    @GetMapping("register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        UserServiceModel user = this.userService.findUserByUsername(userRegisterBindingModel.getUsername());
        if (user == null ) {
            if (userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
                this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
                return "redirect:/users/login";
            }else {
                redirectAttributes.addFlashAttribute("passwordDontMatch", true);
                redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
                return "redirect:/users/register";
            }

        } else {
            redirectAttributes.addFlashAttribute("alreadyExist", true);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            return "redirect:/users/register";
        }


    }

    @GetMapping("/logout")
    private String login(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}