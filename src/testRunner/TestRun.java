package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"C:/Users/Pc/Desktop/It akademija/Python and Java/Java/Vezbe/AutoTesting/src/Features/fullTest.feature"},
        glue = "steps", publish = true, plugin = {"pretty", "html:reports/myreport.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/rerun.txt",})
public class TestRun {
}
