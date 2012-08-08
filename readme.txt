The Moodle Functional Test Automation Harness

This is an open source project that automates some types of testing for Moodle and is based upon
Selenium WebDriver, it includes all Jars required to build the project. Please feel free to generate your own javadocs, 
write your own tests, page objects and utilities or even extend WebDriver to make it more user friendly. Everything in the
project is Open Source.

The project is very incomplete and will be subject to change, classes may be moved and removed and directory structures may change.

This IS NOT a unit testing tool, it exercises the application as a whole through the user interface. If you wish to write unit
tests please use the PHPUnit framework and follow the unit test guidelines at moodle.org.

Possible uses include the automation of:
Functional Testing
Acceptance Testing
Load Testing
Performance Testing
Stress Testing
Installation Testing

Please see the http://www.seleniumhq.org for official support and documentation of Selenium. For training documentation also see: http://www.compendiumdev.co.uk/selenium/ (Selenium 1).

DESIGN CONSIDERATIONS:

Tests are written making use of page object repositories and NOT recorded using Selenium IDE. The object repositories model the behaviour of a Moodle page or even a distinct section of a page e.g. the navigation block.

LIMITATIONS:
The page objects may need to be modified to work with your Moodle installation depending on the implementation of themes. Currently, only very limited testing have been performed on themes.

The Selenium tests can only operate within the context of the browser. Other tools are needed to simulate features such as the uploading files.

KNOWN ISSUES:
The only WebDrivers that have been tested with Moodle are:
RemoteWebdriver; for use with Selenium Server(RC).
ChromeDriver; For local Chrome installations.
FirefoxDriver; For local Firefox installations.

This software is supplied free of charge without any warranty.