package automacao.steps;

import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.TimeoutException;
import java.util.List;
import org.junit.Assert;

public class HomeSteps {

  private HomePage homePage;
  private LoginPage loginPage;
  private List<Usuario> massaDeDados;

  @Dado("que eu esteja na tela inicial")
  public void queEuEstejaNaTelaInicial() {
    try {
      loginPage = Hooks.getLoginPage();
      homePage = Hooks.getHomePage();
      massaDeDados = Hooks.getMassaDeDados();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível acessar a tela inicial: " + e.getMessage());
    }
  }

  @Quando("realizar o login sem saldo")
  public void realizarLoginSemSaldo() {
    try {
      Usuario usuario = massaDeDados.get(0);
      loginPage.inserirEmail(usuario.getEmail());
      loginPage.inserirSenha(usuario.getSenha());
      loginPage.clickAcessar();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível realizar o login: " + e.getMessage());
    }
  }

  @Entao("o nome e a inicial deve ser o mesmo de cadastro")
  public void verificarNomeEInicial() {
    try {
      Usuario usuario = massaDeDados.get(0);
      String nomeAtual = homePage.obterTextName();
      String inicialAtual = homePage.obterTextNameInicial();
      String nomeExpected = usuario.getNome();
      String InicialExpected = String.valueOf(usuario.getNome().charAt(0)).toUpperCase();

      Assert.assertEquals(nomeAtual, nomeExpected);
      Assert.assertEquals(inicialAtual, InicialExpected);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar nome e inicial: " + e.getMessage());
    }
  }

  @Entao("o numero da conta deve ser referente ao login realizado")
  public void verificarNumeroConta() {
    try {
      Usuario usuario = massaDeDados.get(0);
      String contaExpected = usuario.getConta();
      String contaAtual = homePage.obterTextAccountNumber();

      Assert.assertEquals(contaExpected, contaAtual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar o número da conta: " + e.getMessage());
    }
  }

  @Entao("o saldo da conta deve ser zero")
  public void verificarSaldoZero() {
    try {
      String valorActual = homePage.obterTextBalance();
      Assert.assertEquals("0,00", valorActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar o saldo da conta: " + e.getMessage());
    }
  }

  @Quando("realizar o login com saldo")
  public void realizarLoginComSaldo() {
    try {
      Usuario usuario = massaDeDados.get(0);
      loginPage.inserirEmail(usuario.getEmail());
      loginPage.inserirSenha(usuario.getSenha());
      loginPage.clickAcessar();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível realizar o login com saldo: " + e.getMessage());
    }
  }

  @Entao("a conta deve possuir saldo")
  public void verificarSaldoExistente() {
    try {
      String valorActual = homePage.obterTextBalance();
      Assert.assertEquals("1.000,00", valorActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar o saldo da conta: " + e.getMessage());
    }
  }

  @Quando("clicar em tranferencia")
  public void clicarEmTransferencia() {
    try {
      homePage.clickTransferencia();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível clicar em transferência: " + e.getMessage());
    }
  }

  @Entao("a conta deve possuir a função de transferência {string}")
  public void verificarFuncaoTransferencia(String endPoint) {
    try {
      Assert.assertTrue(homePage.getCurrentUrl(endPoint));
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar a função de transferência: " + e.getMessage());
    }
  }

  @Quando("clicar em extrato")
  public void clicarEmExtrato() {
    try {
      homePage.clickExtrato();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível clicar em extrato: " + e.getMessage());
    }
  }

  @Entao("a conta deve possuir a função de extrato {string}")
  public void verificarFuncaoExtrato(String endPoint) {
    try {
      Assert.assertTrue(homePage.getCurrentUrl(endPoint));
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar a função de extrato: " + e.getMessage());
    }
  }

  @Quando("clicar em sair")
  public void clicarEmSair() {
    try {
      homePage.clickExit();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível clicar em sair: " + e.getMessage());
    }
  }

  @Entao("a pagina deve voltar a tela inicial")
  public void verificarRetornoTelaInicial() {
    try {
      Assert.assertTrue(homePage.getCurrentUrl(""));
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar o retorno à tela inicial: " + e.getMessage());
    }
  }
}
