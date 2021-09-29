# ScreenShotGUI
GUI截图小工具，使用ADB命令，在PC端对Android设备截图。


![界面](image/gui.png)



### 实现目标
 - [x] 查看PC连接的所有Android设备
 - [x] 截图
 - [x] 输出目录保存在配置中
 - [x] 清空手机中缓存截图
 - [ ] 帮助菜单：ADB相关命令及其他快捷键
 - [ ] 多语言手动切换
 - [ ] 打包并且使用打包中的Jre，ADB环境使用


#### 开发环境使用
    1.IEDA打开此项目（我使用的IntelliJ IDEA 2021.2.1 (Community Edition)）
    2.Launcher.java 右键Run即可。

#### 打包（todo 暂时不好用 开发中）
    IEDA Maven的Lifecycle执行 package. 在target文件夹下会生成压缩包

#### 脱离Java环境使用（todo 暂时不好用 开发中）
    1.下载release版
    2.解压压缩包后执行start脚本。

##### 参考项目
[android_screen_interactor](https://github.com/djalmaafilho/android_screen_interactor)

[CXTouch](https://github.com/cxplan/CXTouch)


