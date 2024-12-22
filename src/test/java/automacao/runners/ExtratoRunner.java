package automacao.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", tags = "@Extrato", glue = "automacao.steps", plugin = {
        "pretty", "html:target/cucumber-reports/extrato-report.html",
        "json:target/cucumber-reports/extrato-report.json" }, monochrome = true, snippets = CucumberOptions.SnippetType.CAMELCASE, dryRun = false)
public class ExtratoRunner {
}
