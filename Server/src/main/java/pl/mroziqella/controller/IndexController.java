package pl.mroziqella.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mroziqella.exception.ConnectExeception;
import pl.mroziqella.exception.ImageNotFound;
import pl.mroziqella.inte.SharingPicture;
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

    @RequestMapping("/image/{user}")
    public String image(@PathVariable String user, Model model) {
        model.addAttribute("user", user);
        return "displayImage";
    }
//iVBORw0KGgoAAAANSUhEUgAAAKAAAABZCAIAAAA2MLirAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAHtSURBVHhe7dExDgIBEMNA/v/po3HKSIgGsvKUbv16dJqDj3PwcQ4+zsHHOfg4Bx/n4OMcfJyDj/t08Ev/ikOFg+dxqHDwPA4VDp7HocLB8zhUfDmYql/gQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVALB+/hQVCLLwfrf3CocPA8DhUOnsehwsHzOFQ4eB6Hik8Ha5SDj3PwcQ4+zsHHOfg4Bx/n4OMcfJyDT3ueNy+iYHphpeKjAAAAAElFTkSuQmCC
    @RequestMapping(value = "/image/picture/{user}", method = RequestMethod.GET)
    public @ResponseBody String getImage(@PathVariable String user) {
        try {
            return new String(shareService.getImageBase64(user));
        } catch (ImageNotFound e) {
            return new String("error");
        }
    }
}


