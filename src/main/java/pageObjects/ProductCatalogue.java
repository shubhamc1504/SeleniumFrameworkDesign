package pageObjects;
import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    //constructor
    public ProductCatalogue(WebDriver driver)
    {
        //initialize the webDriver inside constructor
        this.driver = driver; //this.driver refers to local driver variable created inside class
        PageFactory.initElements(driver,this); //initialize all the elements inside driver
    }
//    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy(css = ".mb-3")
    List<WebElement> products;

}
