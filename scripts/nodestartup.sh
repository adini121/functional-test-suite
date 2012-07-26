#!/bin/bash
export DISPLAY=:0
/usr/bin/java -jar apps/selenium-server-standalone-2.24.1.jar -role node -hub http://192.168.200.27:4444/grid/register -nodeConfig /var/lib/jenkins/apps/webconfig.txt -Dwebdriver.chrome.driver=/var/lib/jenkins/apps/chromedriver
# > /tmp/gridnode.log 2>&1
