package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInExplicitWait {
    Logger logger = Logger.getLogger(SignInExplicitWait.class);
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

    public SignInExplicitWait(WebDriver webDriver, WebDriverWait webDriverWait) {
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
        username.sendKeys(strUsername);
    }

    public void setPassword(String strPassword) {
        password.sendKeys(strPassword);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void signInGitHub(String strUsername, String strPassword) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(username));
            this.setUsername(strUsername);
            webDriverWait.until(ExpectedConditions.visibilityOf(password));
            this.setPassword(strPassword);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton));
            this.clickSignInButton();
        } catch (TimeoutException exception) {
            logger.error("Throw TimeoutException", exception);
        }
    }
}
