package com.bjsxt.plane;

//import com.bjsxt.plane.GameObject;
import java.awt.Color;
import java.awt.*;
import java.awt.Graphics;
//炮弹
public class Shell extends GameObject {
    double degree;

    public Shell(){
        degree = Math.random()*Math.PI*2;
        x = 200 ;
        y = 200 ;
        width = 10;
        height = 10;
        speed = 7;
    }

    @Override
    public void drawMySelf(Graphics g) {
        //将外部传入对象g的状态保存好
        Color c = g.getColor();
        g.setColor(Color.yellow);

        g.fillOval((int)x,(int)y,width,height);

        //炮弹沿着任意角度飞行
        x += (int)(speed*Math.cos(degree));
        y += (int)(speed*Math.sin(degree));

        //实现边界碰撞回弹
        if (y>GameUtil.FRAME_HEIGHT-height|| y<30){
            degree = -degree;
        }
        if (x>GameUtil.FRAME_WIDTH-width|| x<0){
            degree =Math.PI-degree;
        }

        //返回给外部，变回以前的颜色
        g.setColor(c);
    }
}
