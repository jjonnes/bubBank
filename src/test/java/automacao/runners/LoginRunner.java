package automacao.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/login.feature", glue = "automacao.steps", tags = "@Login", plugin = {
                "pretty",
                "html:target/cucumber-reports/login-report.html",
                "json:target/cucumber-reports/login-report.json" }, monochrome = true, dryRun = false)
public class LoginRunner {
}
