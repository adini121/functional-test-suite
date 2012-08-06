#!/bin/bash
export DISPLAY=:99 && /etc/init.d/xvfb start
/usr/bin/java -jar ../lib/selenium-2.25.0/selenium-server-standalone-2.25.0.jar -role node -hub http://nightly01.test.local:4444/grid/register -nodeConfig ../properties/JSON/FirefoxNode/webconfig.txt
# > /tmp/gridnode.log 2>&1