package autoTesting.tests;

import autoTesting.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;

public class catalogueProduct extends abstractComponent {

    WebDriver driver;
    public catalogueProduct(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".button.wc-backward")
    WebElement backToShop;
    By backToShopButton = By.cssSelector(".button.wc-backward");
    By zippoProducts = By.cssSelector("div[class$='woocommerce columns-4 ']");
    @FindBy(css = "option[selected$='selected']")
    WebElement selected;
    public void BackTOShop(){
        waitForElementToAppear(backToShopButton);
        backToShop.click();

    }
    public void getProduct(){
        WebElement chooseCategory = driver.findElement(By.cssSelector("button[class$='dropbtn']"));
        WebElement zippo = driver.findElement(By.xpath("//li[@id='menu-item-17826']//a[normalize-space()='Zippo']"));
        WebElement zippoProduct = driver.findElement(By.cssSelector("li[id='menu-item-17830'] a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(chooseCategory).click().moveToElement(zippo).moveToElement(zippoProduct).click().perform();
        waitForElementToAppear(zippoProducts);

    }

    public void verifyPage(){
        WebElement element = driver.findElement(By.cssSelector("button[class$='dropbtn']"));
        Assert.assertTrue(element.isDisplayed(), "Ocekivani element nije vidljiv");
    }
    public void dropDownMenu(){
        WebElement dropDown = driver.findElement(By.name("orderby"));
        Select dropdownselect = new Select(dropDown);
        dropdownselect.selectByVisibleText("Sortiraj po ceni: od veće ka manjoj");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(selected.isSelected(), "Ocekivani element nije selektovan");

    }

}
