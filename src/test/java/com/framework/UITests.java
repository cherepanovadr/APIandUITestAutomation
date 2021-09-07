package com.framework;
//Java 11+
//IntelliJ
//Maven
//JUnit Jupiter API 5
//Selenium + chromeDriver
//RestAssured

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.xml.xpath.XPath;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class UITests {
    public static final String BASE_URL = "https://github.com/";
    static WebDriver driver;

    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
   static void cleanup() {
        driver.close();
    }

    @Test
    void userNameIsCorrectOnOverviewTab() {

        String user = "cherepanovadr";
        driver.get(BASE_URL + user);
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();
        assertEquals(user, actualUserName);
    }

    @Test
    void repoLinkGoesToCorrectRepo() {

        String user = "cherepanovadr";
        driver.get(BASE_URL + user);
        String repo = "APIandUITestAutomation";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://github.com/cherepanovadr/" + repo, actualUrl);
    }

    @Test
    void repositoryCountIsCorrect() {
        driver.get("https://github.com/cherepanovadr?tab=repositories");
        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));
        String expectedRepos = driver.findElement(By.xpath("(//span[@title='7'][contains(.,'7')])[1]")).getText();
        int expectedNumber = Integer.parseInt(expectedRepos);
        assertEquals(expectedNumber, repos.size());
    }

}
