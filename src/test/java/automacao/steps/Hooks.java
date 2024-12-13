package automacao.steps;

import automacao.pages.*;
import automacao.utils.DriverFactory;
import automacao.utils.MassaDeDados;
import automacao.utils.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Hooks {

  private static WebDriver driver = DriverFactory.getDriver();
  protected static LoginPage loginPage;
  protected static RegistrarPage registrarPage;
  protected static HomePage homePage;
  // protected static ExtratoPage extratoPage;
  // protected static TransferenciaPage transferenciaPage;
  protected static List<Usuario> massaDeDados;

  @Before(value = "@I")
  public void setUp() {
    driver = DriverFactory.getDriver();

    loginPage = new LoginPage(driver);
    registrarPage = new RegistrarPage(driver);
    homePage = new HomePage(driver);
    // extratoPage = new ExtratoPage(driver);
    // transferenciaPage = new TransferenciaPage(driver);

    driver.get("https://bugbank.netlify.app/");

    System.out.println("Configuração inicial realizada");
  }

  @Before(value = "@MassaComSaldo")
  public void geraMassaDeDadosComSaldo() {
    massaDeDados = MassaDeDados.gerarUsuarios(1);

    for (Usuario usuario : massaDeDados) {
      MassaDeDados.cadastrar(usuario, driver, 1);
    }
  }

  @Before(value = "@MassaSemSaldo")
  public void geraMassaDeDadosSemSaldo() {
    massaDeDados = MassaDeDados.gerarUsuarios(1);

    for (Usuario usuario : massaDeDados) {
      MassaDeDados.cadastrar(usuario, driver, 0);
    }
  }

  @After
  public void tearDown() throws InterruptedException {
    DriverFactory.closeDriver();
    System.out.println("Cenário realizado e driver fechado");
  }

  public static List<Usuario> getMassaDeDados() {
    return massaDeDados;
  }

  public static LoginPage getLoginPage() {
    return loginPage;
  }

  public static RegistrarPage getRegistrarPage() {
    return registrarPage;
  }

  public static HomePage getHomePage() {
    return homePage;
  }
}
