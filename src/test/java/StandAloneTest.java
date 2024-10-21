import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        String productName = "ZARA COAT 3";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LandingPage page = new LandingPage(driver);
        page.goTo();
        //Login with the registered user
        page.loginApplication("shubhamc1504@gmail.com", "Shubhamc@15041998");
        ProductCatalogue productcatalogue = new ProductCatalogue(driver);
        List<WebElement> products= productcatalogue.getProductList();
        productcatalogue.addProductToCart(productName);


//        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//        //check all the products are added
//        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//        boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
//        Assert.assertTrue(match);
//        //click on checkout button
//        driver.findElement(By.cssSelector(".totalRow button")).click();
//
//        Actions a = new Actions(driver);
//        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//        //explicit wait for options to pop up
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
//
//        //put assert to see text is displaying or not
//        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//        driver.close();

    }
}
