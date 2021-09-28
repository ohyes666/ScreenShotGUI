package com.tdx.scgui.ui;

import com.tdx.scgui.impl.CommonDataCallBack;
import com.tdx.scgui.utils.AdbUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel {
    public Main() {
        initView(this);
    }


    private void initView(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("设备列表");
        //x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 80, 200, 100);

        JList jList = new JList(new String[]{"No device"});

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                //释放鼠标出发
//                if (!jList.getValueIsAdjusting()) {
//                    String deviceName = (String) jList.getSelectedValue();
//                    System.out.println("connecting android device = " + deviceName);
//                    AdbUtil.getInstance().selectDevice(deviceName);
//                }
            }
        });
        scrollPane.setViewportView(jList);
        panel.add(scrollPane);
        panel.setVisible(true);

        AdbUtil.getInstance().getDeviceList(new CommonDataCallBack<String[]>() {
            @Override
            public void onSuccess(String[] data) {
                jList.setListData(data);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

        //选择设备
        JButton chooseBtn = new JButton("选择设备");
        chooseBtn.setBounds(100, 220, 100, 25);
        panel.add(chooseBtn);
        chooseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String deviceName = (String) jList.getSelectedValue();
                AdbUtil.getInstance().selectDevice(deviceName);
                AdbUtil.getInstance().createFolderMobile();
            }
        });

        //刷新按钮
        JButton refreshBtn = new JButton("刷新设备");
        refreshBtn.setBounds(100, 20, 100, 25);
        panel.add(refreshBtn);


        //截图名称输入
        JLabel scName = new JLabel("截图名称");
        scName.setBounds(250, 20, 80, 25);
        panel.add(scName);

        JTextField jTextField = new JTextField(30);
        jTextField.setBounds(250, 50, 200, 25);
        panel.add(jTextField);

        JLabel png = new JLabel(".png");
        png.setBounds(450, 50, 40, 25);
        panel.add(png);

        //截图按钮
        JButton screenShotBtn = new JButton("截图");
        screenShotBtn.setBounds(250, 100, 100, 25);
        panel.add(screenShotBtn);

        JLabel screenShotTip = new JLabel("截图到输出目录");
        screenShotTip.setBounds(355, 100, 200, 25);
        panel.add(screenShotTip);

        screenShotBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = jTextField.getText().trim();
                AdbUtil.getInstance().screenShot(name);
            }
        });

        //输出目录
        JButton outPath = new JButton("输出目录");
        outPath.setBounds(250, 150, 100, 25);
        panel.add(outPath);

        JLabel outPathTip = new JLabel("选择截图输出目录");
        outPathTip.setBounds(355, 150, 200, 25);
        panel.add(outPathTip);


        outPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setDialogTitle("选择截图输出目录");
                int result = chooser.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println("out Path = " + path);
                    outPathTip.setText(path);
                    AdbUtil.getInstance().setOutPath(path);
                }
            }
        });


        //打开目录
        JButton openOutBtn = new JButton("打开目录");
        openOutBtn.setBounds(250, 200, 100, 25);
        panel.add(openOutBtn);

        JLabel openOutBtnTip = new JLabel("打开截图输出目录");
        openOutBtnTip.setBounds(355, 200, 200, 25);
        panel.add(openOutBtnTip);

        openOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Desktop.getDesktop().open(new File(AdbUtil.getInstance().getOutPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //截图清空
        JButton clearBtn = new JButton("截图清空");
        clearBtn.setBounds(250, 250, 100, 25);
        panel.add(clearBtn);

        JLabel clearBtnTip = new JLabel("清空手机内部缓存截图");
        clearBtnTip.setBounds(355, 250, 200, 25);
        panel.add(clearBtnTip);

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

    }
}
