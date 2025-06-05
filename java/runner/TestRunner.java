package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources",
        glue = {"stepdefinition"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true,
        tags = "@JHipsterLogin"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}