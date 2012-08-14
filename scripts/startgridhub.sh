#!/bin/bash
#DISPLAY="nightly01.test.local"
java -jar ../lib/selenium-2.25.0/selenium-server-standalone-2.25.0.jar -role hub -Dwebdriver.chrome.driver=lib/chromedriver
# 2>1& > ../tmp/gridhub.log
