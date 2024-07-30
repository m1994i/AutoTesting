package autoTesting.tests;

import autoTesting.abstractComponents.abstractComponent;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class CheckoutPage extends abstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "billing_first_name")
    WebElement firstNameField;
    @FindBy(id = "billing_last_name")
    WebElement lastNameField;
    @FindBy(id = "billing_address_1")
    WebElement streetAndHomeNumberField;
    @FindBy(id = "billing_address_2")
    WebElement streetNumberField;
    @FindBy(id = "billing_city")
    WebElement cityField;
    @FindBy(id = "billing_postcode")
    WebElement postalCodeField;
    @FindBy(id = "billing_phone")
    WebElement phoneField;
    @FindBy(xpath = "//input[@id='terms']")
    WebElement checkBox;
    @FindBy(css = "a[href$='/moj-nalog']")
    WebElement myAccount;
    @FindBy(xpath = "(//a[normalize-space()='Odjavite se'])[1]")
    WebElement logOut;
    By visibleLogOut=By.xpath("(//a[normalize-space()='Odjavite se'])[1]");

    public void getCheckoutInfo() throws IOException, InterruptedException {

        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Pc\\Desktop\\It akademija\\Python and Java\\" +
                "Java\\Vezbe\\AutoTesting\\src\\main\\java\\testing\\orderinfo.json")));
        JSONObject json = new JSONObject(content);
        String name = json.getString("name");
        String lastname = json.getString("lastname");
        String streetAndHomeNumber = json.getString("street and home number");
        String streetNumber = json.getString("street number");
        String city = json.getString("city");
        String postalCode = json.getString("postal code");
        String phoneNumber = json.getString("phone number");

        firstNameField.sendKeys(name);
        lastNameField.sendKeys(lastname);
        streetAndHomeNumberField.sendKeys(streetAndHomeNumber);
        streetNumberField.sendKeys(streetNumber);
        cityField.sendKeys(city);
        postalCodeField.sendKeys(postalCode);
        phoneField.sendKeys(phoneNumber);
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();
        Assert.assertTrue(checkBox.isSelected(), "Checkbox nije selektovan");
        myAccount.click();

    }
    public void getLogOut(){
        waitForElementToAppear(visibleLogOut);
        logOut.click();

    }




}
