package autoTesting.tests;

import autoTesting.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class productPage extends abstractComponent {

    WebDriver driver;

    public productPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(xpath = "//h2[normalize-space()='Zippo Metalic red 49475']")
    WebElement product1;
    @FindBy (className = "plus")
    WebElement plusButton;
    @FindBy(css = "input[type$='number']")
    WebElement expectedValue;
    @FindBy(xpath = "//a[normalize-space()='2']")
    WebElement pageNumber2;
    @FindBy(xpath = "//a[normalize-space()='3']")
    WebElement pageNumber3;
    @FindBy(css = "div[class$='logo']")
    WebElement clickLogo;
    @FindBy(css = "a[href$='/moj-nalog']")
    WebElement mojNalog;

    @FindBy(xpath="(//a[normalize-space()='Odjavite se'])[1]")
    WebElement logOutButton;

    By confirmationMessage = By.cssSelector("div[role='alert']");
    By product1Visible=By.xpath("//h2[normalize-space()='Zippo Metalic red 49475']");
    By pageNumber2Visible=By.xpath("//a[normalize-space()='2']");
    By pageNumber3Visible = By.xpath("//a[normalize-space()='3']");
    By visibleCLickLogo = By.cssSelector("div[class$='logo']");
    public void chooseProductToCart(){
        waitForElementToAppear(pageNumber3Visible);
        pageNumber3.click();
        waitForElementToAppear(product1Visible);
        product1.click();
        int numberPlus = 1;
        for (int i=0; i<numberPlus; i++){
            plusButton.click();

        }
        Assert.assertTrue(expectedValue.isDisplayed(), "Ocekivana kolicina nije prezentovana");


    }
    public void clickButtonCart(){
        WebElement buttonCart = driver.findElement(By.name("add-to-cart"));
        buttonCart.click();
        waitForElementToAppear(confirmationMessage);
        WebElement confirmMess = driver.findElement(By.cssSelector("div[role='alert']"));
        Assert.assertTrue(confirmMess.isDisplayed(), "Proizvod nije dodat u korpu");
        waitForElementToAppear(visibleCLickLogo);
        clickLogo.click();




    }
}