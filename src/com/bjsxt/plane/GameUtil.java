package com.bjsxt.plane;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 500;



    //工具类最好把构造器私有化
    private GameUtil(){

    }

    public static Image getImage(String path){
//        BufferedImage bi = null;
//        try{
//            URL u = GameUtil.class.getClassLoader().getResource(path);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return bi;

        Image img = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try{
            img = ImageIO.read(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;

    }
}
