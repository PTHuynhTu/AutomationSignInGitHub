
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    public WebDriverWait webDriverWait;

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
        webDriverWait = new WebDriverWait(webDriver, 20);
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
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
        webDriver.findElement(By.linkText("Sign in")).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login']")));
        WebElement username = webDriver.findElement(By.xpath("//input[@name='login']"));
        username.sendKeys(getPropertyValue().getProperty("username"));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement password = webDriver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(getPropertyValue().getProperty("password"));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='commit']")));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@name='commit']"));
        signInButton.click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
        Assert.assertEquals(baseURL, webDriver.getCurrentUrl());

    }

}
