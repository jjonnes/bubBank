package automacao.steps;

import automacao.pages.*;
import automacao.utils.DriverFactory;
import automacao.utils.MassaDeDados;
import automacao.utils.PropertyReader;
import automacao.utils.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Hooks {

  protected static WebDriver driver;
  protected static LoginPage loginPage;
  protected static CadastroPage cadastroPage;
  protected static HomePage homePage;
  protected static TransferenciaPage transferenciaPage;
  protected static List<Usuario> massaDeDados;
  protected static ExtratoPage extratoPage;

  @Before(value = "@SemMassa")
  public void setUpI() {
    setUp(false, 0, true);
  }

  @Before(value = "@MassaComSaldo")
  public void setUpII() {
    setUp(true, 2, true);
  }

  @Before(value = "@MassaSemSaldo")
  public void setUpIII() {
    setUp(true, 1, false);
  }

  @After
  public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "Screenshot");
    }
    DriverFactory.closeDriver();
  }

  private void setUp(boolean preCadastro, Integer numeroUsuarios, boolean contaComSaldo) {
    driver = DriverFactory.getDriver();
    PropertyReader config = PropertyReader.getInstance();
    loginPage = new LoginPage(driver, config.getPageTimeout());
    cadastroPage = new CadastroPage(driver, config.getPageTimeout());
    homePage = new HomePage(driver, config.getPageTimeout());
    transferenciaPage = new TransferenciaPage(driver, config.getPageTimeout());
    extratoPage = new ExtratoPage(driver, config.getPageTimeout());

    driver.get(config.getBaseUrl());

    System.out.println("Configuração inicial realizada");

    if (preCadastro) {
      massaDeDados = MassaDeDados.gerarUsuarios(numeroUsuarios);
      for (Usuario usuario : massaDeDados) {
        MassaDeDados.cadastrar(usuario, driver, contaComSaldo);
      }
    }
  }

  public static List<Usuario> getMassaDeDados() {
    return massaDeDados;
  }

  public static LoginPage getLoginPage() {
    return loginPage;
  }

  public static CadastroPage getCadastroPage() {
    return cadastroPage;
  }

  public static HomePage getHomePage() {
    return homePage;
  }

  public static TransferenciaPage getTransferenciaPage() {
    return transferenciaPage;
  }

  public static ExtratoPage getExtratoPage() {
    return extratoPage;
  }
}