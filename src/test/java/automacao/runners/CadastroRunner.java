package automacao.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/cadastro.feature", glue = "automacao.steps", tags = "@Cadastro", plugin = {
    "pretty",
    "html:target/cucumber-reports/cadastro-report.html",
    "json:target/cucumber-reports/cadastro-report.json"
}, monochrome = true, dryRun = true)
public class CadastroRunner {
}
