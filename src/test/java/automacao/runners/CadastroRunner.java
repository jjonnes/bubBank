package automacao.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "automacao.steps", tags = "@Cadastro", plugin = {
                "pretty",
                "html:target/cucumber-reports/cadastro-report.html",
                "json:target/cucumber-reports/cadastro-report.json"
}, snippets = CucumberOptions.SnippetType.CAMELCASE, monochrome = true, dryRun = false)
public class CadastroRunner {
}
