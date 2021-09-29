package com.tdx.scgui.utils;


import com.tdx.scgui.impl.CommonDataCallBack;

import java.io.File;

/**
 * ADB 单例工具类
 */
public class AdbUtil {

    private static AdbUtil mInstance;

    //手机内部缓存截图目录
    private final String mScreenShotPathMobile = "/sdcard/Pictures/screen_shots/";
    private final String mScreenCachePathMobile = mScreenShotPathMobile + "cache/";

    private Runtime mRuntime;
    private String mAdbPath;

    private String mConnectingDevice;//当前连接设备ID

    private AdbUtil() {
    }

    public static AdbUtil getInstance() {
        if (mInstance == null) {
            synchronized (AdbUtil.class) {
                if (mInstance == null) {
                    mInstance = new AdbUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初期化 获取当前连接的所有Android设备
     */
    public void init() {
        mRuntime = Runtime.getRuntime();
        mAdbPath = initAdbPath();
    }

    /**
     * 初始化ADB路径 优先使用Android环境中的adb 其次是用项目包中的adb  以上都没有 就直接使用adb 当作环境变量配置好了。
     *
     * @return ADB路径
     */
    private String initAdbPath() {
        String adbPath = "";
        String android_home = System.getenv("ANDROID_HOME");
        if (android_home != null) {
            adbPath = android_home + File.separator + "platform-tools" + File.separator + "adb";
        } else {
            File builtADB = new File("res/adb");
            if (builtADB.isDirectory() && builtADB.exists()) {
                String osDir = Util.getOsDir();
                adbPath = "res/adb/" + osDir + "/adb";
            } else {
                adbPath = "adb";
            }
        }
        return adbPath;
    }

    public void createFolderMobile() {
        //创建截图文件夹
        try {
            CommandUtil.createFolder(mAdbPath, "/sdcard/Pictures/", mRuntime);
            CommandUtil.createFolder(mAdbPath, mScreenShotPathMobile, mRuntime);
            CommandUtil.createFolder(mAdbPath, mScreenCachePathMobile, mRuntime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDeviceList(CommonDataCallBack<String[]> dataCallBack) {
        try {
            String data = CommandUtil.getDevicesList(mAdbPath, mRuntime);

            String[] lines = data.split("\n");
            if (lines.length == 0) {
                System.out.println("no connecting android device");
                dataCallBack.onFailed(null);
                return;
            }

            String list = "";
            int i = 0;
            for (String line : lines) {
                if (i > 0) {
                    list += " " + line;
                }
                i++;
            }
            String[] devices = list.trim().split(" ");
            if (devices.length > 0) {
                dataCallBack.onSuccess(devices);
            } else {
                dataCallBack.onFailed(null);
            }
        } catch (Exception e) {
            dataCallBack.onFailed(e);
            e.printStackTrace();
        }
    }

    /**
     * 根据选择设备修改命令前的参数
     *
     * @param deviceName
     */
    public void selectDevice(String deviceName) {
        String commandParam = "";
        String[] data = deviceName.split("\t");
        if (data.length > 1) {
            mConnectingDevice = data[0];
            String type = data[1];
            if ("device".equalsIgnoreCase(type)) {
                commandParam = " -s ";
            } else {
                commandParam = " -d ";
            }
            mAdbPath += commandParam + mConnectingDevice + " ";
        }

    }

    /**
     * 截图
     *
     * @param shotName 截图名称（不包含.png）
     */
    public int screenShot(String shotName) {
        int error = AppError.SUCCESS;
        if (shotName == null || shotName.length() == 0) {
            return AppError.EMPTY_FILE_NAME;
        }
        if (getOutPath() == null || getOutPath().length() == 0) {
            return AppError.EMPTY_OUT_PATH;
        }
        try {
            CommandUtil.screenshot(shotName, mScreenShotPathMobile, mAdbPath, mRuntime);
            CommandUtil.pullScreenshot(mScreenShotPathMobile + shotName, getOutPath(), mAdbPath, mRuntime);
        } catch (Exception e) {
            e.printStackTrace();
            error = AppError.UN_KNOW;
        }
        return error;
    }

    public String getOutPath() {
        return Setting.getInstance().getOutPutPath();
    }
}
