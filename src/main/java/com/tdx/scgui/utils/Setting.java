package com.tdx.scgui.utils;

import java.io.File;
import java.io.IOException;


public class Setting {
    private static Setting mInstance;

    private String mUserPath = "";

    private String mOutPutPath = "";

    private Setting() {
    }

    public static Setting getInstance() {
        if (mInstance == null) {
            synchronized (AdbUtil.class) {
                if (mInstance == null) {
                    mInstance = new Setting();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        mUserPath = System.getProperty("user.home") + File.separator + "." + Constants.APP_NAME + File.separator;
        File file = new File(mUserPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        loadSetting();
    }

    private void loadSetting() {
        String settingFile = mUserPath + File.separator + Constants.SETTING_FILE_NAME;
        ParamProperties properties = new ParamProperties();
        try {
            properties.loadTextFile(settingFile);
            mOutPutPath = properties.getProperty("outPath");
        } catch (IOException e) {
            System.out.println("loadSetting" + e.getMessage());
        }
    }

    public void changeOutPutPath(String outPath) {
        if (outPath.equals(mOutPutPath)) {
            return;
        }
        mOutPutPath = outPath;
        String settingFile = mUserPath + File.separator + Constants.SETTING_FILE_NAME;
        ParamProperties properties = new ParamProperties();
        properties.setProperty("outPath", outPath);
        try {
            properties.saveToFile(settingFile);
        } catch (IOException e) {
            System.out.println("changeOutPutPath" + e.getMessage());
        }
    }

    public String getOutPutPath() {
        return mOutPutPath;
    }
}
