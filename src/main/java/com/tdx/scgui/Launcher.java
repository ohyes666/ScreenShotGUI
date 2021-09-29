package com.tdx.scgui;


import com.tdx.scgui.ui.Main;
import com.tdx.scgui.ui.Menu;
import com.tdx.scgui.utils.AdbUtil;
import com.tdx.scgui.utils.Setting;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Launcher extends JFrame {

    public static void main(String[] args) {
        AdbUtil.getInstance().init();
        // 显示应用 GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Setting.getInstance().init();
                Launcher main = new Launcher();
            }
        });

    }

    public Launcher() {
        setTitle("ADB Tool");
        setSize(600, 500);
        setLocationRelativeTo(null);
        // 创建 JFrame 实例
        JFrame frame = new JFrame("ADB Tool");
        // Setting the width and height of frame
        frame.setSize(600, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JPanel panel = new Main();
        frame.add(panel);

        Menu menu = new Menu();
        frame.setJMenuBar(menu);

        frame.setVisible(true);
    }

}
