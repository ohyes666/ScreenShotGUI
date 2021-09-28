package com.tdx.scgui.utils;

public class Util {
    /**
     * Return the name of current os used for naming resource directory.
     * This application is cross-platform, so the resources should
     * be built as different version for certain OS, the name of directory must match with current OS.
     *
     */
    public static String getOsDir() {
        if (isWindow()) {
            return "win";
        } else if (isLinux()) {
            return "linux";
        } else if (isMac()) {
            return "macos";
        } else {
            throw new RuntimeException("Unknown OS");
        }
    }

    /**
     * Validate whether current os is window
     *
     * @return true if os is mac, otherwise false.
     */
    public static boolean isWindow() {
        String osName = System.getProperty("os.name");
        osName = osName.toLowerCase();
        if (osName.indexOf("window") > -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate whether current os is linux
     *
     * @return true if os is mac, otherwise false.
     */
    public static boolean isLinux() {
        String osName = System.getProperty("os.name");
        osName = osName.toLowerCase();
        if (osName.indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate whether current os is mac os x
     *
     * @return true if os is mac, otherwise false.
     */
    public static boolean isMac() {
        String osName = System.getProperty("os.name");
        osName = osName.toLowerCase();
        if (osName.indexOf("mac") > -1) {
            return true;
        } else {
            return false;
        }
    }
}
