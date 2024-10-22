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
import pageObjects.*;

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
        ProductCatalogue productcatalogue = page.loginApplication("shubhamc1504@gmail.com", "Shubhamc@15041998");
        List<WebElement> products= productcatalogue.getProductList();
        productcatalogue.addProductToCart(productName);
        CartPage cartpage = productcatalogue.goToCartPage();
        Boolean match = cartpage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartpage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}
