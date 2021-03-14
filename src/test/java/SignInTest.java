import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignInTest {
    private final String driver = "webdriver.chrome.driver";
    private final String linkDriver = "C:\\chromedriver.exe";
    private final String baseURL = "https://github.com/";

    @Test
    public void testGetHomePage() {
        System.setProperty(driver, linkDriver);
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(baseURL);
        String expectedTitle = "GitHub: Where the world builds software · GitHub";
        Assert.assertEquals(expectedTitle, driver.getTitle());
        Assert.assertEquals(baseURL, driver.getCurrentUrl());
    }

    @Test
    public void testSignInPage() {
        System.setProperty(driver, linkDriver);
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(baseURL);
        driver.findElement(By.linkText("Sign in")).click();

        WebElement username = driver.findElement(By.xpath("//input[@name='login']"));
        username.sendKeys("PTHuynhTu");

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("Tu@0906062717");

        WebElement signInButton = driver.findElement(By.xpath("//input[@name='commit']"));
        signInButton.click();

        String expectedTitle = "GitHub";
        Assert.assertEquals(expectedTitle, driver.getTitle());
        Assert.assertEquals(baseURL, driver.getCurrentUrl());
    }

}
