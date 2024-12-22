package automacao.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "automacao.steps", tags = "@Login", plugin = {
    "pretty",
    "html:target/cucumber-reports/login-report.html",
    "json:target/cucumber-reports/login-report.json" }, snippets = CucumberOptions.SnippetType.CAMELCASE, monochrome = true, dryRun = false)
public class LoginRunner {
}
