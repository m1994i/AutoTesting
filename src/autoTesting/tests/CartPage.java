package autoTesting.tests;

import autoTesting.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CartPage extends abstractComponent {
    WebDriver driver;



    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//span[@class='wc-block-mini-cart__quantity-badge'])[2]")
    WebElement buttonCart;
    @FindBy(xpath = "(//span[normalize-space()='Pregled korpe'])[1]")
    WebElement cartReview;
    @FindBy(css = ".checkout-button.button.alt.wc-forward")
    WebElement continueWithPayment;
    By showCartReview = By.xpath("(//span[normalize-space()='Pregled korpe'])[1]");
    By showButtonCart = By.xpath("(//span[@class='wc-block-mini-cart__quantity-badge'])[2]");
    By visibleContinueWithPayment = By.cssSelector(".checkout-button.button.alt.wc-forward");

    public void getCartPage(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        waitForElementToAppear(showButtonCart);
        buttonCart.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        waitForElementToAppear(showCartReview);
        cartReview.click();
        waitForElementToAppear(visibleContinueWithPayment);
        continueWithPayment.click();


    }


}
