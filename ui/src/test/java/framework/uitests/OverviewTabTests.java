package framework.uitests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverviewTabTests extends BaseTestClass {
    String user = "cherepanovadr";

    @BeforeEach
    void overviewSetup() {
        driver.get(BASE_URL + user);
    }

    @Test
    void userNameIsCorrectOnOverviewTab() {
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();
        assertEquals(user, actualUserName);
    }

    @Test
    void repoLinkGoesToCorrectRepo() {
        String repo = "APIandUITestAutomation";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://github.com/cherepanovadr/" + repo, actualUrl);
    }

}
