#!/bin/bash
/usr/bin/java -jar ../lib/selenium-2.25.0/selenium-server-standalone-2.25.0.jar -role node -hub http://nightly01.test.local:4444/grid/register -nodeConfig ../properties/JSON/ChromeNode/webconfig.txt -Dwebdriver.chrome.driver=../lib/chromedriverOSX
# > /tmp/gridnode.log 2>&1