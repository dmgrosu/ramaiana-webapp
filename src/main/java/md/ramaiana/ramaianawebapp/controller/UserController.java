package md.ramaiana.ramaianawebapp.controller;

import md.ramaiana.ramaianawebapp.model.dto.AppUserDto;
import md.ramaiana.ramaianawebapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(value = "/registration")
    public String showRegistrationForm(Model model) {
        AppUserDto userDto = new AppUserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("user") @Valid AppUserDto userDto,
                               BindingResult bindingResult, Model model) {

        model.addAttribute("user", userDto);

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "password do not match!");
        }

        if (appUserService.findUserByEmail(userDto.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "e-mail is not unique!");
        }

        if (!bindingResult.hasErrors()) {
            appUserService.registerNewUser(userDto);
        } else {
            return "registration";
        }

        return "redirect:/registration?success";
    }


}
