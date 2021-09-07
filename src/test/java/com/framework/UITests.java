package com.framework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class UITests {

    //Java 11+
    //IntelliJ
    //Maven
    //JUnit Jupiter API 5
    //Selenium + chromeDriver
    //RestAssured

    @Test
    void userNameIsCorrectOnOverviewTab(){
        //Arrange
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String user = "cherepanovadr";
        driver.get("https://github.com/"+user);
        //Act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert
        Assertions.assertEquals(user,actualUserName);

        driver.close();
    }
    @Test
    void repoLinkGoesToCorrectRepo(){
        //Arrange
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String user = "cherepanovadr";
        driver.get("https://github.com/"+user);

        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert
        Assertions.assertEquals(user,actualUserName);

        driver.close();
    }

}
