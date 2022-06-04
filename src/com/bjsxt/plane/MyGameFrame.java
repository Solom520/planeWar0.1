package com.bjsxt.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static com.bjsxt.plane.GameUtil.*;
public class MyGameFrame extends JFrame {
    Image bgImg = GameUtil.getImage("images/bg.jpg");
    Image planeImg = GameUtil.getImage("images/plane.png");
    Plane plane = new Plane(planeImg,300,300,10);
    ArrayList<Shell> shellList = new ArrayList<Shell>();


    //初始化窗口
    public void launchFrame(){
        this.setTitle("飞机大战 BY SOLOM");   //打印标题
        this.setVisible(true);    //窗口默认不可见，设为可见
        setSize(FRAME_WIDTH,FRAME_HEIGHT);//窗口大小：500*500
        setLocation(300,300); //窗口左上角顶点得坐标位置

        //初始化50发炮弹
        for (int i=0;i<50;i++){
            Shell b = new Shell();
            shellList.add(b);
        }

        //关闭按钮
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }

        });


        //启动窗口绘制线程
        new PaintThread().start();

        //键盘监听
        this.addKeyListener(new KeyMonitor());
    }

    //重写Paint方法
    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        //从顶点坐标（100，50） 到（400，400）画一条直线
//        g.drawLine(100,50,400,400);
        //画出矩形，矩形左上角顶点坐标（100，50），宽400，高300
//        g.drawRect(100,50,300,300);
        //画出椭圆，椭圆外切矩形为：左上角顶点（100，50），宽度300，高度300
//        g.drawOval(100,50,300,300);
//        Image bg = GameUtil.getImage("images/bg.jpg");
//        update(g);
        g.drawImage(bgImg,0,0,FRAME_WIDTH,FRAME_HEIGHT,null);
//        g.drawImage(planeImg,planeX,planeY,null);
//        planeX+=3;

        //画出飞机本身
        plane.drawMySelf(g);
        for (int i=0;i< shellList.size();i++){
            Shell b = shellList.get(i);
            b.drawMySelf(g);
        }
    }

    //重画线程
    class PaintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(20);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //键盘监听的内部类
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            super.keyPressed(e);
//            System.out.println(e.getKeyCode());
//              plane.x+=3;
            plane.addDirection(e);
        }




        @Override
        public void keyReleased(KeyEvent e) {
//            super.keyReleased(e);
//            System.out.println(e.getKeyCode());
            plane.minusDirection(e);
        }

    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();


    }
    //采用双缓冲技术，防止屏幕闪烁
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(FRAME_WIDTH,FRAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
