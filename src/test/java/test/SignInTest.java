package test.java.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.pageFactory.SignInExplicitWait;
import test.java.pageFactory.SignInImplicitWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SignInTest {

    Logger logger = Logger.getLogger(SignInTest.class);
    private final String baseURL = "https://github.com/";
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    public SignInExplicitWait signInExplicitWait;
    public SignInImplicitWait signInImplicitWait;

    public Properties getPropertyValue() {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
                return prop;
            } catch (IOException e) {
                logger.error("Throw IOException");
            }
        }
        return null;
    }

    @BeforeMethod
    public void getDriver() {
        System.setProperty(getPropertyValue().getProperty("driver"), getPropertyValue().getProperty("linkDriver"));
        webDriver = new ChromeDriver();
        webDriver.get(baseURL);
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    @AfterMethod
    public void closeDrive() {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        webDriver.quit();
    }

    @Test(priority = 2)
    public void testSignInPage_ExplicitWait() {
        signInExplicitWait = new SignInExplicitWait(webDriver);
        signInExplicitWait.clickSignInLink();
        signInExplicitWait.signInGitHub(getPropertyValue().getProperty("username"), getPropertyValue().getProperty("password"));
        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());

    }

    @Test(priority = 1)
    public void testSignInPage_ImplicitWait() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        signInImplicitWait = new SignInImplicitWait(webDriver);
        signInImplicitWait.clickSignInLink();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='login'] h1")));
        signInImplicitWait.signInGitHub(getPropertyValue().getProperty("username"), getPropertyValue().getProperty("password"));

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='dashboard-repos-container']")));

        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());

//        webDriver.findElement(By.cssSelector("details.js-feature-preview-indicator-container summary[class='Header-link']")).click();
//        webDriver.findElement(By.cssSelector("form[class='logout-form'] button[type='submit']")).click();
    }
}
