package com.tdx.scgui.ui;

import com.tdx.scgui.i18n.StringManager;
import com.tdx.scgui.i18n.StringManagerFactory;

import javax.swing.*;

public class Menu extends JMenuBar {

    private static final StringManager mStringManager = StringManagerFactory.getStringManager(Menu.class);

    public Menu() {
        createMenu();
    }

    public void createMenu() {
        JMenu settingMenu = new JMenu(mStringManager.getString("menu.setting"));

        JMenuItem lanItem = new JMenuItem(mStringManager.getString("menu.language"));
        settingMenu.add(lanItem);

        JMenu helpMenu = new JMenu(mStringManager.getString("menu.help"));

        JMenu aboutItem = new JMenu(mStringManager.getString("menu.about"));

        add(settingMenu);
        add(helpMenu);
        add(aboutItem);
    }
}
