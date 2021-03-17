package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
    Logger logger = Logger.getLogger(SignIn.class);
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;

    @FindBy(linkText = "Sign in")
    WebElement signInLink;

    @FindBy(xpath = "//input[@name='login']")
    WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//input[@name='commit']")
    WebElement signInButton;

    public SignIn(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(webDriver, this);
    }

    public void clickSignInLink() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(signInLink));
            signInLink.click();
        } catch (TimeoutException exception) {
            logger.error("Throw TimeoutException", exception);
        }

    }

    public void setUsername(String strUsername) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(username));
            username.sendKeys(strUsername);
        } catch (TimeoutException exception) {
            logger.error("Throw TimeoutException", exception);
        }
    }

    public void setPassword(String strPassword) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(password));
            password.sendKeys(strPassword);
        } catch (TimeoutException exception) {
            logger.error("Throw TimeoutException", exception);
        }

    }

    public void clickSignInButton() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(signInButton));
            signInButton.click();
        } catch (TimeoutException exception) {
            logger.error("Throw TimeoutException", exception);
        }

    }

    public void signInGitHub(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.clickSignInButton();
    }
}
