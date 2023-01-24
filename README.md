# MobileTesting-TheScoreApp

Created the repository for the assignment on the Scrore App. 

I have created the maven project and used testNG framework with Page Object Model.

Implemented parameterized approach to run the test for multiple sets of test data.

# Tech Stack

1. Java
2. Appium
3. TestNG

# Clone Project

To clone the repository use **git clone https://github.com/SatyaTechQa/TheScoreApp-Assignment.git**

# Adding Android Device & Appium Server Url in Configs

Add the android device capabilities under **device_capabilities.json** file for the device you wish to execute.

Add the appium server url in **appium_server_urls.json** to start appium session on given port.

# TestNG Implementation

**BaseTest.java** is responsible to perform pre-requisite steps and post execution steps.

The class path to **BaseTest.java** is **TheScoreApp-Assignment/src/main/common/BaseTest.java**

  1. **@BeforeSuite**  - Creates the test results folder and html for extent report
    
  2. **@BeforeClass**  - Intializes the Android Driver Object
    
  (Used the above annotations as per the current need)
  
# Page Object Model Approach

The package **TheScoreApp-Assignment/src/main/pagefactory/pageObjects** contains page objects classes.

(Currently implemented Page Objects for Login, Settings & Leagues pages are given below)

1. **LoginPageObject**
2. **SettingsPageObject**
3. **LeaguesPageObject**

**PageFactory.java** is resposible to create objects for all page object classes.

# Reusable Package

I have created ReUsable class to maintain all the reusable functions throught the framework

The class path for **ReUsable.java** is **TheScoreApp-Assignment/src/main/pagefactory/pageobjects/reusable/ReUsable.java** 
  
# Tests

Test is added to verify leagues in **LeagueTest.java**

The class path to **LeagueTest.java** is **TheScoreApp-Assignment/src/test/TestCases/LeagueTest.java**

**Test Steps**

1. Performs Login to TheScore App (UserName & Password values should be provided in testng.xml file)
2. Open Leagues Page based on the Parameter (Ex: League NBA)
3. Verifies the Data presence under League
4. Click on Sub Tab under League Page (Ex: Standings)
5. Verifies the data prescene under Sub tab.
6. Navigates to Back and verifies landing page
7. Performs Logout from the app

# Adding tests in testng.xml

Currently one test is added to verify Leagues with working test data. Repeated the same test to twice to parameterized approach.
However, user can add mutliple tests(as class names/package names) with different sets of test data to execute the tests.

# Parameterized approach

I have followed the parameterized approach,by passing data to below variables.

    1. Device
    2. UserName
    3. Password
    
The parameters should be provided in testng.xml 

# Test Execution

User can run the test from **testng.xml** file as **Run as TestNG Suite**

