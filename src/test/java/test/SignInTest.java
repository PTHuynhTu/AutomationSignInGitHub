package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactory.SignInExplicitWait;
import pageFactory.SignInImplicitWait;

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
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    @AfterMethod
    public void closeDrive() {
        webDriver.quit();
    }

    @Test(priority = 0)
    public void testSignInPage_ExplicitWait() {
        webDriver.get(baseURL);
        signInExplicitWait = new SignInExplicitWait(webDriver, webDriverWait);
        signInExplicitWait.clickSignInLink();
        signInExplicitWait.signInGitHub(getPropertyValue().getProperty("username"), getPropertyValue().getProperty("password"));
        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());

    }

    @Test(priority = 1)
    public void testSignInPage_ImplicitWait() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(baseURL);
        signInImplicitWait = new SignInImplicitWait(webDriver);
        signInImplicitWait.clickSignInLink();
        signInImplicitWait.signInGitHub(getPropertyValue().getProperty("username"), getPropertyValue().getProperty("password"));
        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());

    }

}
