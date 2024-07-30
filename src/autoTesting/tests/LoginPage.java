package autoTesting.tests;

import autoTesting.abstractComponents.abstractComponent;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class LoginPage extends abstractComponent{
    WebDriver driver;


    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "a[href$='/moj-nalog']")
    WebElement mojNalog;
    @FindBy(id = "username")
    WebElement userNameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(name = "login")
    WebElement getLogIn;
    By descriptionLogIn = By.cssSelector("p[class$='cart-empty woocommerce-info']");

    public void LogInInfo() throws IOException {
        mojNalog.click();
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Pc\\Desktop\\It akademija\\Python and Java\\Java\\" +
                "Vezbe\\AutoTesting\\src\\main\\java\\testing\\logInInfo.json")));
        JSONObject json = new JSONObject(content);
        String username = json.getString("username");
        String password = json.getString("password");
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);

    }
    public void getClickLogin(){

        getLogIn.click();
    }
    public void verifyLogIn(){
        waitForElementToAppear(descriptionLogIn);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement description = driver.findElement(descriptionLogIn);
        String actualMessage = description.getText();
        String expectedMessage = "Vaša korpa je trenutno prazna.";
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertTrue(description.isDisplayed(), "Ocekivana poruka nije prikazana");

    }
    public void goTo(){

        driver.get("https://ekspedicija.rs");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("cn-accept-cookie")).click();
    }


}
