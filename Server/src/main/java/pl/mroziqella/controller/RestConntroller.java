package pl.mroziqella.controller;




import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import pl.mroziqella.domain.Coord;
import pl.mroziqella.domain.User;
import pl.mroziqella.inte.Image;
import pl.mroziqella.inte.MouseInfo;
import pl.mroziqella.repository.server.Server;

import java.rmi.RemoteException;



/**
 * Created by Kamil on 12/05/2016.
 */
@RestController
@RequestMapping("/rest")
public class RestConntroller {

    private static final Logger logger = Logger.getLogger(RestConntroller.class.getName());
    @RequestMapping(value = "/login/{loginRoom}",method = RequestMethod.GET)
    public boolean isUser(@PathVariable String loginRoom,@RequestParam String password){

        try {
            logger.info("Zalogowano!");
            return IndexController.server.isRoom(loginRoom,password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }

    }
    @RequestMapping(value = "/sizeImage/{loginRoom}",method = RequestMethod.GET)
    public Coord sizeImage(@PathVariable String loginRoom){
        Coord coord = new Coord();
        coord.setX(Server.imageData.get(loginRoom).getSize()[0]);
        coord.setY(Server.imageData.get(loginRoom).getSize()[1]);
        return coord;

    }

    @RequestMapping(value = "/coord/{loginRoom}",method = RequestMethod.POST)
    public Coord setCoord(@PathVariable String loginRoom,@RequestBody Coord coord){

        logger.info("wyslano! x:"+coord.getX()+" y:"+coord.getY());
        Coord coord1 = coord;
        double zoom = Server.imageData.get(loginRoom).getZoom();
        Server.mouseData.put(loginRoom,new MouseInfo((int)(coord.getX()*zoom),(int)(coord.getY()*zoom)));

        return coord1;
    }

}
