package test.java.pageFactory;

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
    public WebDriverWait webDriverWait10;

    @FindBy(linkText = "Sign in")
    WebElement signInLink;

    @FindBy(css = "input[id='login_field']")
    WebElement username;

    @FindBy(css = "input[id='password']")
    WebElement password;

    @FindBy(css = "input[type='submit']")
    WebElement signInButton;

    public SignInExplicitWait(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait10 = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    public void clickSignInLink() {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(signInLink));
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
            webDriverWait10.until(ExpectedConditions.visibilityOf(username));
            this.setUsername(strUsername);
            webDriverWait10.until(ExpectedConditions.visibilityOf(password));
            this.setPassword(strPassword);
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(signInButton));
            this.clickSignInButton();
        } catch (TimeoutException exception) {
            logger.error("Throw TimeoutException", exception);
        }
    }
}
