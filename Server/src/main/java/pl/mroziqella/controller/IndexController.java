package pl.mroziqella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mroziqella.exception.ImageNotFound;
import pl.mroziqella.repository.server.Server;
import pl.mroziqella.service.ShareService;

import java.rmi.RemoteException;

/**
 * Created by Kamil on 21/03/2016.
 */
@Controller
public class IndexController {

    @Autowired
    private ShareService shareService;
    public static Server server;

    /**
     * Uruchamia serwer RMI
     */
    public IndexController() {
        try {
            server = new Server();
            server.start(2000, "server");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String home() {

        return "home";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Wyswietla strone transmisji
     * @param room
     * @param model
     * @return
     */
    @RequestMapping("/image/{room}")
    public String image(@PathVariable String room,@RequestParam("password")String password, Model model) {
        try {
            if(!server.isRoom(room,password)){
                model.addAttribute("info","Brak dostepu");
                return "info";
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        model.addAttribute("user", room);
        return "displayImage";
    }

    /**
     * Zwraca obraz do transmisji
     * @param room
     * @return
     */

//iVBORw0KGgoAAAANSUhEUgAAAKAAAABZCAIAAAA2MLirAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAHtSURBVHhe7dExDgIBEMNA/v/po3HKSIgGsvKUbv16dJqDj3PwcQ4+zsHHOfg4Bx/n4OMcfJyDj/t08Ev/ikOFg+dxqHDwPA4VDp7HocLB8zhUfDmYql/gQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVCLLwfrf3CocPA8DhUOnsehwsHzOFQ4eB6Hik8Ha5SDj3PwcQ4+zsHHOfg4Bx/n4OMcfJyDT3ueNy+iYHphpeKjAAAAAElFTkSuQmCC
    @RequestMapping(value = "/image/picture/{room}", method = RequestMethod.GET)
    public @ResponseBody String getImage(@PathVariable String room) {
        try {
            return new String(shareService.getImageBase64(room));
        } catch (ImageNotFound e) {
            return new String("error");
        }
    }
}


