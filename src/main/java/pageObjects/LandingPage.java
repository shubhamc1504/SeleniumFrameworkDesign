package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    //constructor
    public LandingPage(WebDriver driver)
    {
        super(driver);
        //initialize the webDriver inside constructor
        this.driver = driver; //this.driver refers to local driver variable created inside landingpage
        PageFactory.initElements(driver,this); //initialize all the elements inside driver
    }
//    WebElement userEmail = driver.findElement(By.id("userEmail"));
    //Using Page Factory Class
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submit;

    public void loginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
