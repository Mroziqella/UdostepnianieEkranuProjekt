package pl.mroziqella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.mroziqella.domain.User;
import pl.mroziqella.inte.SharingPicture;
import pl.mroziqella.repository.server.Server;

import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by Kamil on 21/03/2016.
 */
@Controller
public class IndexController {
    @Autowired
    private SharingPicture sharingPicture;

    public IndexController() {
        try {
            Server server = new Server();
            server.start(2000, "server");
        }catch (RemoteException e){

        }
    }

    @RequestMapping("/")
    public String home(){

        return "login";
    }

    @RequestMapping("/page")
    public String page(){
        try {
            System.out.println(new String(sharingPicture.readImageFromServer("user")));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "home";
    }

    @RequestMapping("/login")
    public String loginPage()
    {
        return "login";
    }

    @RequestMapping("/image")
    public String image()
    {
        return "displayImage";
    }


    @RequestMapping(value = "/image/{user}",method = RequestMethod.GET)
    public @ResponseBody String getImage(@PathVariable String user){
        try {
            return new String(sharingPicture.readImageFromServer(user));
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new IllegalAccessError("Nie znaleziono transmisji");
        }
    }
}
