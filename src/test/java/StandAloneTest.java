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

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");

        //Register with new user
//        driver.findElement(By.xpath("(//a[normalize-space()='Register'])[1]")).click();
//        driver.findElement(By.id("firstName")).sendKeys("Shubham");
//        driver.findElement(By.id("lastName")).sendKeys("Choudhury");
//        driver.findElement(By.id("lastName")).sendKeys("Choudhury");
//        driver.findElement(By.id("userEmail")).sendKeys("shubhamc1504@gmail.com");
//        driver.findElement(By.id("userMobile")).sendKeys("8286448862");
//        driver.findElement(By.cssSelector("input[value='Male']")).click();
//        driver.findElement(By.id("userPassword")).sendKeys("Shubhamc@15041998");
//        driver.findElement(By.id("confirmPassword")).sendKeys("Shubhamc@15041998");
//        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
//        driver.findElement(By.cssSelector("#login")).click();
        //Login with the registered user

        String productName1 = "ZARA COAT 3";
        driver.findElement(By.id("userEmail")).sendKeys("shubhamc1504@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Shubhamc@15041998");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        //Ecommerce website functionality
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod1 =products.stream().filter(product-> product.findElement(By.cssSelector("b"))
                        .getText()
                        .equals(productName1)).findFirst().orElse(null);
        assert prod1 != null;
        prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        //check all the products are added
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName1));
        Assert.assertTrue(match);
        //click on checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
        //explicit wait for options to pop up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".ng-star-inserted")).click();

        //put assert to see text is displaying or not
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();

    }
}
