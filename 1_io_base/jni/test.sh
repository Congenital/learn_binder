##########################################################################
# File Name: test.sh
# Author: Congenital
# mail: Congenital_andy@163.com
# Created Time: 二  3/15 16:17:47 2022
#########################################################################
#!/bin/zsh
ndk-build
adb push ../libs/armeabi-v7a/test /data/local/tmp/
adb shell chmod 0777 /data/local/tmp/test
adb shell /data/local/tmp/test
