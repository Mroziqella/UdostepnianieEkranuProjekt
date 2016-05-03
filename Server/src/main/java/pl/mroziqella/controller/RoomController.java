package pl.mroziqella.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mroziqella.domain.Room;
import pl.mroziqella.domain.User;
import pl.mroziqella.service.RoomService;
import pl.mroziqella.service.UserService;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by Kamil on 03/05/2016.
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    MessageSource messageSource;
    private static Logger logger = Logger.getLogger(RoomController.class);


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){

        //pobiernie nazwy uzytkownika z sesji
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();


        Room newRoom = new Room();
        model.addAttribute("newRoom",newRoom);
        return "addRoom";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(Locale locale, @ModelAttribute("newRoom") @Valid Room newRoom, BindingResult result, Model model){
        //pobiernie nazwy uzytkownika z sesji
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        newRoom.setUser(userService.getUser(name));

        if(!roomService.save(newRoom)){
            result.reject("nameRoom", messageSource.getMessage("validation.addRoom.roomName.label",null,locale));
        }
        logger.debug("======================================================================================== \n"+result.getFieldError());
        if (result.getErrorCount()>0) {
            return "addRoom";
        }
        model.addAttribute("info", new String("Pokój " + newRoom.getRoomName() + " zarejestrowano przez "+ name+ "\n Hasło "+ newRoom.getRoomPassword()));
        return "info";
    }
}
