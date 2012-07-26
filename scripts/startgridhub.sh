#!/bin/bash
DISPLAY="nightly01.test.local"
sudo java -jar /var/lib/jenkins/selenium-server-standalone-2.24.1.jar -role hub 2>1& > /var/lib/jenkins/tmp/gridhub.log
