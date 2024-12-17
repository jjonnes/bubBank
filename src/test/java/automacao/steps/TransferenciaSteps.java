package automacao.steps;

import automacao.pages.ExtratoPage;
import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.pages.TransferenciaPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import java.util.List;

import org.junit.Assert;

public class TransferenciaSteps {

  private TransferenciaPage transferenciaPage;
  private LoginPage loginPage;
  private HomePage homePage;
  private ExtratoPage extratoPage;
  private List<Usuario> massaDeDados;

  @Dado("que eu estou na página de transferência")
  public void que_eu_estou_na_página_de_transferência() {
    loginPage = Hooks.getLoginPage();
    homePage = Hooks.getHomePage();
    extratoPage = Hooks.getExtratoPage();
    transferenciaPage = Hooks.getTransferenciaPage();
    massaDeDados = Hooks.getMassaDeDados();

    Usuario usuario = massaDeDados.get(0);

    String userEmail = usuario.getEmail();
    String userPass = usuario.getSenha();

    loginPage.inserirEmail(userEmail);
    loginPage.inserirSenha(userPass);
    loginPage.clickAcessar();

    homePage.clickTransferencia();
  }

  @Quando("inserir o número da conta inexistente {string} e o dígito {string}")
  public void inserir_o_número_da_conta_inexistente_e_o_dígito(String conta, String digito) {
    transferenciaPage.inserirNumeroConta(conta);
    transferenciaPage.inserirDigito(digito);
  }

  @Quando("inserir o campo descrição com {string}")
  public void inserir_o_campo_descrição_com(String descricao) {
    transferenciaPage.inserirDescricao(descricao);
  }

  @Quando("inserir o valor da transferência com {string}")
  public void inserir_o_valor_da_transferência_com(String valor) {
    transferenciaPage.inserirValorTransferencia(valor);
  }

  @Quando("clicar no botão Tranferir agora")
  public void clicar_no_botão_tranferir_agora() {
    transferenciaPage.clicarTransferir();
  }

  @Entao("eu devo ver a mensagem {string}")
  public void eu_devo_ver_a_mensagem_de_erro(String string) {
    String modalActual = transferenciaPage.obterMensagemDeAlerta();

    Assert.assertEquals(string, modalActual);
  }

  @Quando("inserir o número da conta")
  public void inserir_o_número_da_conta() {
    Usuario usuario = massaDeDados.get(1);

    String conta = usuario.getConta();

    transferenciaPage.inserirNumeroConta(conta);
    transferenciaPage.inserirDigito(conta);
  }

  @Entao("eu devo ser redirecionado para a página de extrato {string}")
  public void eu_devo_ser_redirecionado_para_a_página_de_extrato(String endPoint) {
    transferenciaPage.fecharModal();

    Assert.assertTrue(homePage.getCurrentUrl(endPoint));
  }

  @Entao("eu devo ter dedução no saldo de {string}")
  public void eu_devo_ter_dedução_no_saldo_de(String valorDeducao) {
    transferenciaPage.fecharModal();
    transferenciaPage.clicarVoltar();

    String saldoActual = homePage.obterTextBalance();
    double saldoEsperadoDouble = 1000.00 - Integer.valueOf(valorDeducao);
    String saldoExpected = String.format("%.2f", saldoEsperadoDouble)
        .replace(".", ",");

    Assert.assertEquals(saldoExpected, saldoActual);
  }

  @Entao("a conta destino deve receber {string} com a descrição {string}")
  public void a_conta_destino_deve_receber_com_a_descrição(String valor, String descriptionExpected) {
    Usuario usuario = massaDeDados.get(1);

    transferenciaPage.fecharModal();
    transferenciaPage.clickExit();

    loginPage.inserirEmail(usuario.getEmail());
    loginPage.inserirSenha(usuario.getSenha());
    loginPage.clickAcessar();

    homePage.clickExtrato();

    String saldoActual = extratoPage.obterValorTransferencia();
    String descricaoActual = extratoPage.obterDescricaoTransferencia();

    double valorDouble = Double.parseDouble(valor);
    String valorExpected = String.format("%.2f", valorDouble).replace(".", ",");

    Assert.assertEquals(valorExpected, saldoActual);
    Assert.assertEquals(descriptionExpected, descricaoActual);
  }

  @Quando("inserir o valor da transferência {string}")
  public void inserir_o_valor_da_transferência(String valor) {
    transferenciaPage.inserirValorTransferencia(valor);
  }

  @Entao("validar que o campo número da conta aceita apenas o padrão {string}")
  public void validar_que_o_campo_numero_da_conta_aceita_apenas_o_padrao(String regex) {
    boolean resultado = transferenciaPage.caracteresAceitosNumeroConta(regex);
    Assert.assertTrue(
        "O campo número da conta está aceitando caracteres fora do padrão especificado: " + regex +
            ". O campo deve aceitar apenas caracteres que correspondam ao regex.",
        resultado);
  }

  @Entao("validar que o campo dígito aceita apenas o padrão {string}")
  public void validar_que_o_campo_digito_aceita_apenas_o_padrao(String regex) {
    boolean resultado = transferenciaPage.caracteresAceitosDigitoConta(regex);
    Assert.assertTrue(
        "O campo dígito da conta está aceitando caracteres fora do padrão especificado: " + regex +
            ". O campo deve aceitar apenas caracteres que correspondam ao regex.",
        resultado);
  }
}
