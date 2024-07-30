package testing;

import autoTesting.tests.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class autoTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setDriver() {
        //Postavka exten report izvestaja
        String path = System.getProperty("user.dir") + "\\reports\\report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Test");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Milan Ivanovic");
        //postavka chrome driver-a
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void LogIN() throws IOException {
        test = extent.createTest("LogIN Test", "Testiranje logovanja");
        LoginPage loginPage = new LoginPage(driver);
        try {
            loginPage.goTo();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            loginPage.LogInInfo();
            loginPage.getClickLogin();
            loginPage.verifyLogIn();
            test.pass("Logovanje uspesno");
        } catch (AssertionError | IOException error) {
            test.fail("Logovanje nije uspelo" + error.getMessage());
            throw error;
        }
    }

    @Test(dependsOnMethods = {"LogIN"})
    public void CataloguePage() throws IOException {
        LogIN();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        test = extent.createTest("Catalogue Page Test", "Testiranje funkcije kataloga");
        catalogueProduct CatalogueProd = new catalogueProduct(driver);
        CatalogueProd.BackTOShop();
        CatalogueProd.verifyPage();
        test.pass("Katalog uspesno otvoren");
        CatalogueProd.getProduct();
        test.pass("Ocekivani proizvodi su prikazani");
        CatalogueProd.dropDownMenu();
        test.pass("Ocekivana opcija iz dropdown menija selektovana");
    }

    @Test(dependsOnMethods = {"CataloguePage"})
    public void productPage() throws IOException, InterruptedException {
        CataloguePage();
        test = extent.createTest("Product Page test", "Testiranje funkcije proizvoda");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        productPage ProductPage = new productPage(driver);
        ProductPage.chooseProductToCart();
        test.pass("Ocekivana kolicina je ispravna");
        ProductPage.clickButtonCart();
        test.pass("Proizvod uspesno dodat u korpu");
        CartPage cartPage = new CartPage(driver);
        cartPage.getCartPage();
        test.pass("Korpa uspesno verifikovana");
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getCheckoutInfo();
        test.pass("Porudzbina uspesno zavrsena");
        checkoutPage.getLogOut();
        test.pass("Odjava uspesno zavrsena");
    }

    @AfterMethod
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}