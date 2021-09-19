# ScreenShotGUI
GUI截图小工具，使用ADB命令，在PC端对Android设备截图。


![界面](image/gui.png)

````
command = adb devices
command = adb -s 3JJ0218B05005520  shell mkdir /sdcard/Pictures/
command = adb -s 3JJ0218B05005520  shell mkdir /sdcard/Pictures/screen_shots/
command = adb -s 3JJ0218B05005520  shell mkdir /sdcard/Pictures/screen_shots/cache/
out Path = D:\ScreenShotCache
command = adb -s 3JJ0218B05005520  shell screencap /sdcard/Pictures/screen_shots/test_1.png
command = adb -s 3JJ0218B05005520  pull /sdcard/Pictures/screen_shots/test_1.png D:\ScreenShotCache
