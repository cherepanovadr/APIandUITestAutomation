package com.framework;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoTabTests extends BaseTestClass{

    @Test
    void repositoryCountIsCorrect() {
        driver.get("https://github.com/cherepanovadr?tab=repositories");
        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));
        String expectedRepos = driver.findElement(By.xpath("(//span[@title='7'][contains(.,'7')])[1]")).getText();
        int expectedNumber = Integer.parseInt(expectedRepos);
        assertEquals(expectedNumber, repos.size());
    }

}
