package pl.mroziqella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mroziqella.domain.User;
import pl.mroziqella.service.UserService;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by Kamil on 30/03/2016.
 */
@Controller
public class UserController {
    @Autowired
    UserService user;
    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("newUser", new User());
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Locale locale, @ModelAttribute("newUser") @Valid User newUser, BindingResult result, Model model) {

        if (!newUser.getPassword().equals(newUser.getPassword2())) {
            result.reject("password", messageSource.getMessage("validation.register.password.label",null,locale));
        }
        if (result.hasErrors()) {
            return "registerPage";
        }
        if(!user.save(newUser)){
            result.reject("login", messageSource.getMessage("validation.register.login.label",null,locale));
        }
        if (result.hasErrors()) {
            return "registerPage";
        }
        model.addAttribute("info", new String("Użytkownika" + newUser.getLogin() + " zarejestrowano"));
        return "info";
    }

}
