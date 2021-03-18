package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInImplicitWait {
    Logger logger = Logger.getLogger(SignInImplicitWait.class);
    public WebDriver webDriver;

    @FindBy(linkText = "Sign in")
    WebElement signInLink;

    @FindBy(xpath = "//input[@name='login']")
    WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//input[@name='commit']")
    WebElement signInButton;

    public SignInImplicitWait(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickSignInLink() {
        try {
            signInLink.click();
        } catch (NoSuchElementException exception) {
            logger.error("Throw NoSuchElementException", exception);
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

    public void signInGitHub(String username, String password) {
        try {
            this.setUsername(username);
            this.setPassword(password);
            this.clickSignInButton();
        } catch (NoSuchElementException exception) {
            logger.error("Throw NoSuchElementException", exception);
        }
    }
}
