package pageObjects;
import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
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
        super(driver);
        //initialize the webDriver inside constructor
        this.driver = driver; //this.driver refers to local driver variable created inside class
        PageFactory.initElements(driver,this); //initialize all the elements inside driver
    }
//    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");

    By addToCart = By.cssSelector((".card-body button:last-of-type"));

    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod1 =getProductList().stream().filter(product-> product.findElement(By.cssSelector("b"))
                .getText()
                .equals(productName)).findFirst().orElse(null);
        return prod1;
    }

    public void addProductToCart(String productName)
    {
        WebElement prod1 = getProductByName(productName);
        prod1.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);

    }
}
