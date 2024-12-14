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

  private static WebDriver driver;
  protected static LoginPage loginPage;
  protected static CadastroPage cadastroPage;
  protected static HomePage homePage;
  protected static TransferenciaPage transferenciaPage;
  protected static List<Usuario> massaDeDados;
  protected static ExtratoPage extratoPage;

  @Before(value = "@I")
  public void setUp() {
    driver = DriverFactory.getDriver();

    loginPage = new LoginPage(driver);
    cadastroPage = new CadastroPage(driver);
    homePage = new HomePage(driver);
    transferenciaPage = new TransferenciaPage(driver);
    extratoPage = new ExtratoPage(driver);

    driver.get("https://bugbank.netlify.app/");

    System.out.println("Configuração inicial realizada");
  }

  @Before(value = "@MassaComSaldo")
  public void geraMassaDeDadosComSaldo() {
    massaDeDados = MassaDeDados.gerarUsuarios(2);

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
