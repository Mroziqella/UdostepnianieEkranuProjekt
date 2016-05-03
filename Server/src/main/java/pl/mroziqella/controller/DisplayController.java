package pl.mroziqella.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kamil on 03/05/2016.
 */
@Controller
@RequestMapping("/image")
public class DisplayController {

    /**
     * Udostepnia do widoku pojedynczego stinga
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String searchTransmisnion(Model model)
    {
        model.addAttribute("roomName",new String());
        return "search";
    }

    /**
     * Pobiera z widoku pojedynczego stringa
     * @param roomName
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String searchTransmisnionPOST(@RequestParam("roomName")String roomName)
    {
        return "redirect:/image/"+roomName;
    }
}
