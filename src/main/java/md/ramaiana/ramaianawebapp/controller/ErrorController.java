package md.ramaiana.ramaianawebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @GetMapping(value = "/authorisationError")
    public String show401Error() {
        return "error/401";
    }

}
