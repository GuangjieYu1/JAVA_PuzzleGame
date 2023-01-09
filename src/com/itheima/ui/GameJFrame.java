package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {
    private int[][] imageIndex = new int[4][4];
    private int[][] imageVictory = new int[4][4];
    private static final int ICON_HEIGHT = 105;
    private static final int ICON_WIDTH = 105;

    //记录空白方块在二维数组中的位置
    private int zeroXCoord = 0;
    private int zeroYCoord = 0;
    //记录当前展示图片的路径
    String path = "image/girl/girl4/";


    //JFrame 界面窗体

    public GameJFrame(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片
        initImage();

        //让界面显示出来，建议写在最后
        this.setVisible(true);
    }
    private void initData() {
        randomArr();
    }
    private void initImage() {
//        //创建一个图片ImageIcon的对象
//        ImageIcon icon = new ImageIcon("image/animal/animal1/1.jpg");
//        //创建一个JLabel的对象（管理容器）
//        JLabel jLabel = new JLabel(icon);
//        //指定图片位置
//        jLabel.setBounds(0,0,105,105);
//        this.getContentPane().add(jLabel);

        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取当前要加载图片的序号
                int number = imageIndex[i][j];
                //创建一个JLable的对象
                JLabel jLabel = new JLabel(new ImageIcon(path+number+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*i+83,105*j+134,ICON_WIDTH,ICON_HEIGHT);
                //给图片添加边框
                //0:表示让图片凸起来    1:表示让图片凹下去
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片（代码中后添加的，塞在下方）
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

        /*String pathFront = "image/animal/animal1/";
        String pathEnd = ".jpg";
        ArrayList<ArrayList<JLabel>> jLabelArrArr = new ArrayList<>();
        int len = 100;
        int[] imageIndex = new int[len];
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
        }*/
    }
    public void randomArr(){
        int length = 16;
        int[] arrRes= new int[length];
        Random r = new Random();
        for (int i = 0; i < arrRes.length; i++) {
            arrRes[i] = i;
        }
        for (int i = 0; i < arrRes.length; i++) {
            this.imageVictory[i/4][i%4] = arrRes[i]+1;
        }
        imageVictory[3][3] = 0;
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                System.out.print(this.imageVictory[i][i1]+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < arrRes.length; i++) {
            int index = r.nextInt(arrRes.length);
            int temp = arrRes[i];
            arrRes[i] = arrRes[index];
            arrRes[index] = temp;
        }
        for (int i = 0; i < arrRes.length; i++) {
            this.imageIndex[i/4][i%4] = arrRes[i];
            if(arrRes[i] == 0){this.zeroXCoord = i/4;this.zeroYCoord = i%4;}
            System.out.print(arrRes[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                System.out.print(this.imageIndex[i][i1]+" ");
            }
            System.out.println();
        }

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
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
        //取消默认的居中放置
        this.setLayout(null);
    }
    private void victory(){
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                if(imageIndex[i][i1] == imageVictory[i][i1]){}
                else{return;}
            }
        }
        //清除页面并且弹出胜利
        shortPath();
    }
    private void shortPath(){
        this.getContentPane().removeAll();
        JLabel background = new JLabel(new ImageIcon("image/win.png"));
        background.setBounds(300,300,197,73);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有的图片删除
            this.getContentPane().removeAll();
            JLabel jLabel = new JLabel(new ImageIcon(path+"all.jpg"));
            //指定图片位置
            jLabel.setBounds(83,134,420,420);
            //给图片添加边框
            //0:表示让图片凸起来    1:表示让图片凹下去
            jLabel.setBorder(new BevelBorder(0));
            this.getContentPane().add(jLabel);
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //对上，下，左，右进行判断
        //左：37，上：38，右：39，下：40
        int code = e.getKeyCode();
        if(code == 37){
            //左移到边界
            if (zeroXCoord==0){return;}
            //向左移动
            //调换x,y与x-1,y
            imageIndex[zeroXCoord][zeroYCoord] = imageIndex[zeroXCoord-1][zeroYCoord];
            imageIndex[zeroXCoord-1][zeroYCoord] = 0;
            zeroXCoord -= 1;
            initImage();
        }
        else if(code == 38){
            //上移到边界
            if(zeroYCoord == 0){return;}
            //向上移动
            //调换x,y与x,y-1
            imageIndex[zeroXCoord][zeroYCoord] = imageIndex[zeroXCoord][zeroYCoord-1];
            imageIndex[zeroXCoord][zeroYCoord-1] = 0;
            zeroYCoord -= 1;
            initImage();
        }
        else if(code == 39){
            //右移到边界
            if(zeroXCoord == 3){return;}
            //向右移动
            //调换x,y与x+1,y
            imageIndex[zeroXCoord][zeroYCoord] = imageIndex[zeroXCoord+1][zeroYCoord];
            imageIndex[zeroXCoord+1][zeroYCoord] = 0;
            zeroXCoord += 1;
            initImage();
        }
        else if(code == 40){
            //下移动到边界
            if(zeroYCoord == 3){return;}
            //向下移动
            //调换x,y与x,y+1
            imageIndex[zeroXCoord][zeroYCoord] = imageIndex[zeroXCoord][zeroYCoord+1];
            imageIndex[zeroXCoord][zeroYCoord+1] = 0;
            zeroYCoord += 1;
            initImage();
        }
        //一键还原图
        else if(code == 65){
            initImage();
        }
        //作弊码
        else if(code == 66){
            for (int i = 0; i < 4; i++) {
                for (int i1 = 0; i1 < 4; i1++) {
                    imageIndex[i][i1] = imageVictory[i][i1];
                    System.out.print(this.imageVictory[i][i1]+" ");
                }
            }
            initImage();
        }
    }
}
