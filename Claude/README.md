# Automation Framework (Selenium 3.141.59 + Appium java-client 7.6.0 + WinAppDriver)

## Prerequisites
- JDK 8 or 11, Maven 3.6+
- Chrome installed (web tests)
- Windows: WinAppDriver installed, Developer Mode ON (desktop tests)

## Run
Web only:      mvn clean test -Dgroups=web
Headless web:  mvn clean test -Dgroups=web -Dweb.headless=true
Desktop only:  start WinAppDriver.exe first (as Administrator), then
               mvn clean test -Dgroups=desktop
Everything:    mvn clean test
