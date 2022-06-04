package com.bjsxt.plane;

import java.awt.*;

/**
 * 我们发现，窗口中所有的对象(飞机、炮弹等等)都有很多共性：
 * “图片对象、坐标位置、运行速度、宽度和高度”。为了方便程序开发，
 * 我们需要设计一个GameObject类，它可以所有游戏物体的父类，方便我们编程。
 */
public class GameObject {
    Image img;  //该物体对应的图片对象
    double x,y;  //该物体的坐标
    int speed;   //该物体的运行速度
    int width,height;   //物体所在矩形区域的宽高

    /**
     * 怎么样绘制文本对象
     */
    public void drawMySelf(Graphics g){
        g.drawImage(img,(int)x,(int)y,null);
    }

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;

        //如果有图片，就让他跟图片一样大
        if(img!=null){
            this.width = img.getWidth(null);
            this.height= img.getHeight(null);
        }
    }

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject() {
    }

    /**
     * 返回物体对应矩形区域，便于后续在碰撞检测中使用
     */
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,width,height);
    }

}
