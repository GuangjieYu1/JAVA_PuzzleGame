package com.itheima.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame {
    //表示登录界面
    public LoginJFrame(){
        //初始化登录界面
        initJFrame();

        //初始化按钮
        initButton();

    }
    private void initJFrame(){
        //设置界面的宽高
        this.setSize(488,430);
        //设置界面的标题
        this.setTitle("拼图游戏登陆");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //让界面显示出来，建议写在最后
        this.setVisible(true);
    }
    private void initButton(){
        JButton jtb = new JButton("登录");
        jtb.setBounds(0,0,10,10);
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("登录按钮被按了");
            }
        });
        this.add(jtb);
    }
}
