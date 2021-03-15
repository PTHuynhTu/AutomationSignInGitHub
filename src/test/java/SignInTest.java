
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SignInTest {

    Logger logger = Logger.getLogger(SignInTest.class);
    private final String baseURL = "https://github.com/";

    public WebDriver webDriver;

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
    }

    @AfterMethod
    public void closeDrive() {
        webDriver.quit();
    }

    @Test
    public void testGetHomePage() {
        webDriver.get(baseURL);
        String expectedTitle = "GitHub: Where the world builds software · GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());
    }

    @Test
    public void testSignInPage() {
        webDriver.get(baseURL);
        webDriver.findElement(By.linkText("Sign in")).click();

        WebElement username = webDriver.findElement(By.xpath("//input[@name='login']"));
        username.sendKeys(getPropertyValue().getProperty("username"));

        WebElement password = webDriver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(getPropertyValue().getProperty("password"));

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@name='commit']"));
        signInButton.click();

        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());

    }

}
