package steps;

import autoTesting.tests.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class stepDefinition{
    WebDriver driver = new ChromeDriver();
    LoginPage loginPage = new LoginPage(driver);
    catalogueProduct CatalogueProd  = new catalogueProduct(driver);
    productPage ProductPage = new productPage(driver);
    CartPage cartPage =new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Given("The user is on the login page")
    public void navigateToLoginPage() {
        driver.manage().window().maximize();
        loginPage.goTo();

    }

    @When("User insert valid credentials \\(username: {string}, password: {string})")
    public void user_insert_credentials_username_password(String user, String pass) throws IOException {
        loginPage.LogInInfo();
    }

    @And("User click on sign in button")
    public void user_click_on_log_in_button() {
        loginPage.getClickLogin();

    }

    @Then("User will get account page")
    public void user_will_get_account_page() {
        loginPage.verifyLogIn();
    }
    @And("User click on button back to shop")
    public void user_click_on_button_back_to_shop() {
        CatalogueProd.BackTOShop();
        CatalogueProd.verifyPage();
    }

    @Then("User navigate to catalogue page")
    public void user_navigate_to_catalogue_page() {
        CatalogueProd.getProduct();
    }

    @Then("User sort catalogue by dropdown menu")
    public void user_sort_catalogue_by_dropdown_menu() {
        CatalogueProd.dropDownMenu();
    }
    @Then("User click on page number three then click on product and click on plus button one time")
    public void userGettingProductPage() {
        ProductPage.chooseProductToCart();
    }

    @Then("User click to button cart adn click to logo button to get primary page")
    public void userGettingPrimaryPage() {
        ProductPage.clickButtonCart();
    }

    @Then("User click on button cart and click button cart review and click on checkout button")
    public void userGettingCheckoutPage() {
        cartPage.getCartPage();
    }

    @Then("User entering checkout info and click to checkbox and click to my account button")
    public void userFinishOrder() throws IOException, InterruptedException {
        checkoutPage.getCheckoutInfo();
    }

    @Then("User click on log out button")
    public void userGetLogOut() {
        checkoutPage.getLogOut();
        driver.quit();
    }

}
