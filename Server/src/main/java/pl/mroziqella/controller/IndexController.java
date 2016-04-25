package pl.mroziqella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    public IndexController() {
        try {
            Server server = new Server();
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


    @RequestMapping(value = "/image/picture/{user}", method = RequestMethod.GET)
    public @ResponseBody String getImage(@PathVariable String user) {
        try {
            return new String(shareService.getImageBase64(user));
        } catch (ImageNotFound e) {
            return new String("error");
        }
    }
}


