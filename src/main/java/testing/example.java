package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class example {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ekspedicija.rs/checkout/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement checkBox = driver.findElement(By.xpath("//input[@class='woocommerce-form__input woocommerce-form__input-checkbox input-checkbox']"));
        checkBox.click();
    }
}