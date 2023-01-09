package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {
    private int count = 0;
    private int[][] imageIndex = new int[4][4];
    private final int[][] imageVictory = new int[4][4];
    private static final int ICON_HEIGHT = 105;
    private static final int ICON_WIDTH = 105;

    //记录空白方块在二维数组中的位置
    private int zeroXCord = 0;
    private int zeroYCord = 0;
    //记录当前展示图片的路径
    String path = "image/girl/girl1/";


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

        //如果胜利直接
        if(victory()){
            shortPath();
        }

        //计数器
        JLabel counter = new JLabel("步数: "+this.count);
        counter.setBounds(50,30,100,20);
        this.getContentPane().add(counter);

        //加载图片碎片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取当前要加载图片的序号
                int number = this.imageIndex[i][j];
                //创建一个JLabel的对象
                JLabel jLabel = new JLabel(new ImageIcon(path+number+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*j+83,105*i+134,ICON_WIDTH,ICON_HEIGHT);
                //给图片添加边框
                //0:表示让图片凸起来    1:表示让图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片（代码中后添加的，塞在下方）
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

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
            if(arrRes[i] == 0){this.zeroXCord = i/4;this.zeroYCord = i%4;}
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
        JMenu selectJMenu = new JMenu("选择图片");
        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登陆");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem accountItem = new JMenuItem("公众号");
        JMenuItem animalItem = new JMenuItem("动物");
        JMenuItem sportItem = new JMenuItem("运动");
        JMenuItem girlItem = new JMenuItem("美女");
        //给条目绑定事件
        replayItem.addActionListener(e -> {
            System.out.println("重新游戏");
            //打乱数组
            initData();
            //计步器归零
            count= 0;
            //重新加载图片
            initImage();
        });
        reLoginItem.addActionListener(e -> {
            //关闭当前界面
            setVisible(false);
            //打开登陆界面
            new LoginJFrame();
        });
        closeItem.addActionListener(e -> {
            //关闭系统
            System.exit(0);
        });
        accountItem.addActionListener(e -> {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.setTitle("公众号");
            jDialog.add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        });
        animalItem.addActionListener(e -> {
            Random r = new Random();
            int ind = r.nextInt(8)+1;
            String i = ind+"";
            path = "image/animal/animal"+i+"/";
            initData();
            initImage();
        });
        girlItem.addActionListener(e -> {
            Random r = new Random();
            int ind = r.nextInt(11)+1;
            String i = ind+"";
            path = "image/girl/girl"+i+"/";
            initData();
            initImage();
        });
        sportItem.addActionListener(e -> {
            Random r = new Random();
            int ind = r.nextInt(10)+1;
            String i = ind+"";
            path = "image/sport/sport"+i+"/";
            initData();
            initImage();
        });

        //将每一个选项下面的条目添加到选项当中
        selectJMenu.add(animalItem);
        selectJMenu.add(sportItem);
        selectJMenu.add(girlItem);
        functionJMenu.add(selectJMenu);
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
    private boolean victory(){
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                if(imageIndex[i1][i] != this.imageVictory[i1][i]){return false;}
            }
        }
        return true;
    }
    private void shortPath(){
        JLabel win = new JLabel(new ImageIcon("image/win.png"));
        win.setBounds(203,283,197,73);
        this.getContentPane().add(win);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()){return;}
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有的图片删除
            this.getContentPane().removeAll();
            JLabel jLabel = new JLabel(new ImageIcon(path+"all.jpg"));
            //指定图片位置
            jLabel.setBounds(83,134,420,420);
            //给图片添加边框
            //0:表示让图片凸起来    1:表示让图片凹下去
            jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
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
        if (victory()){return;}
        int code = e.getKeyCode();
        if(code == 37){
            //下移动到边界
            if(zeroYCord == 3){return;}
            //向下移动
            //调换x,y与x,y+1
            imageIndex[zeroXCord][zeroYCord] = imageIndex[zeroXCord][zeroYCord +1];
            imageIndex[zeroXCord][zeroYCord +1] = 0;
            zeroYCord += 1;
            initImage();
            count++;
        }
        else if(code == 38){
            //右移到边界
            if(zeroXCord == 3){return;}
            //向右移动
            //调换x,y与x+1,y
            imageIndex[zeroXCord][zeroYCord] = imageIndex[zeroXCord +1][zeroYCord];
            imageIndex[zeroXCord +1][zeroYCord] = 0;
            zeroXCord += 1;
            initImage();
            count++;
        }
        else if(code == 39){
            //上移到边界
            if(zeroYCord == 0){return;}
            //向上移动
            //调换x,y与x,y-1
            imageIndex[zeroXCord][zeroYCord] = imageIndex[zeroXCord][zeroYCord -1];
            imageIndex[zeroXCord][zeroYCord -1] = 0;
            zeroYCord -= 1;
            initImage();
            count++;
        }
        else if(code == 40){
            //左移到边界
            if (zeroXCord ==0){return;}
            //向左移动
            //调换x,y与x-1,y
            imageIndex[zeroXCord][zeroYCord] = imageIndex[zeroXCord -1][zeroYCord];
            imageIndex[zeroXCord -1][zeroYCord] = 0;
            zeroXCord -= 1;
            initImage();
            count++;
        }
        else if(code == 65){
            initImage();
        }
        //作弊码
        else if(code == 66){
            this.imageIndex = this.imageVictory;
            initImage();
        }
        System.out.print(count+" ");
    }


}
