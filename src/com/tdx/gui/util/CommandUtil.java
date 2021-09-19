package com.tdx.gui.util;

import java.io.File;

public class CommandUtil {

    public static String getDevicesList(String adbPath, Runtime runtime) throws Exception {
        String command = adbPath + " devices ";
        System.out.println("command = " + command);
        Process p = runtime.exec(command);
        p.waitFor();
        int is;
        String str = "";
        while ((is = p.getInputStream().read()) != -1) {
            str += (char) is;
        }
        return str;
    }

    /**
     * 手机内部创建文件夹
     *
     * @param runtime
     * @throws Exception
     */
    public static void createFolder(String adbPath, String folder, Runtime runtime) throws Exception {
        String command = adbPath + " shell mkdir " + folder;
        System.out.println("command = " + command);
        Process p = runtime.exec(command);
        p.waitFor();
    }

    /**
     * 截图 保存在手机内部
     *
     * @param picName 截图名称
     * @param path    手机内部目录
     */
    public static void screenshot(String picName, String path, String adbPath, Runtime runtime) throws Exception {
        String command = adbPath + " shell screencap " + path + picName + ".png ";
        System.out.println("command = " + command);
        Process p = runtime.exec(command);
        p.waitFor();
    }

    /**
     * 从手机中传输文件到电脑
     */
    public static void pullScreenshot(String fileName, String outPath, String adbPath, Runtime runtime) throws Exception {
        String command = adbPath + " pull " + fileName + ".png " + outPath;
        System.out.println("command = " + command);
        Process p = runtime.exec(command);
        p.waitFor();
    }
}
