package com.itheima.ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GameJFrame extends JFrame {
    private static final int ICON_HEIGHT = 105;
    private static final int ICON_WIDTH = 105;
    //JFrame 界面窗体
    //
    public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化图片
        initImage();

        //让界面显示出来，建议写在最后
        this.setVisible(true);
    }

    private void initImage() {
//        //创建一个图片ImageIcon的对象
//        ImageIcon icon = new ImageIcon("image/animal/animal1/1.jpg");
//        //创建一个JLabel的对象（管理容器）
//        JLabel jLabel = new JLabel(icon);
//        //指定图片位置
//        jLabel.setBounds(0,0,105,105);
//        this.getContentPane().add(jLabel);

        String pathFront = "image/animal/animal1/";
        String pathEnd = ".jpg";
        ArrayList<ArrayList<JLabel>> jLabelArrArr = new ArrayList<>();
        int[] imageIndex = new int[16];
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
        int ind = 0;
        for (int i = 1; i < 5; i++) {
            ArrayList<JLabel>  jLabelArr = new ArrayList<>();
            for (int j = 1; j < 5; j++) {
                String pathMid = (imageIndex[ind]+1)+"";
                String path = pathFront+pathMid+pathEnd;
                JLabel jLabel = new JLabel(new ImageIcon(path));
                System.out.println(path);
                jLabelArr.add(jLabel);
                ind++;
            }
            jLabelArrArr.add(jLabelArr);
        }
        System.out.println("here");

        int yCurr = 0;
        for (int i = 0; i < 4; i++) {
            int xCurr = 0;
            for (int j = 0; j < 4; j++) {
                jLabelArrArr.get(i).get(j).setBounds(xCurr,yCurr,ICON_WIDTH,ICON_HEIGHT);
                this.getContentPane().add(jLabelArrArr.get(i).get(j));
                xCurr+= ICON_WIDTH+15;
            }
            yCurr += ICON_HEIGHT+15;
        }
    }
    private int[] randomIndex(int length){
        Random r = new Random();
        HashSet<Integer> setOrigin = new HashSet<>();
        int[] arrResult = new int[length];
        for (int i = 0; i < length; i++) {
           setOrigin.add(i);
        }
        return arrResult;

    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项的对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登陆");
        JMenuItem closeItem = new JMenuItem("关闭游戏");


        JMenuItem accountItem = new JMenuItem("公众号");
        //将每一个选项下面的条目添加到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);


        aboutJMenu.add(accountItem);
        //将菜单里面的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("拼图游戏单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置
        this.setLayout(null);
    }
}
