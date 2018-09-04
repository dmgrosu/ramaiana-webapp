package md.ramaiana.ramaianawebapp.controller;

import md.ramaiana.ramaianawebapp.model.dto.AppUserDto;
import md.ramaiana.ramaianawebapp.validation.ValidationResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class LoginController {


    @PostMapping
    public ValidationResponse login(@ModelAttribute("user") @Valid AppUserDto userDto,
                                    BindingResult bindingResult, Model model) {

        ValidationResponse result = new ValidationResponse();

        return result;
    }


}
