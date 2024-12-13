package automacao.steps;

import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import java.util.List;

import org.junit.Assert;

public class HomeSteps {

  private HomePage homePage;
  private LoginPage loginPage;
  private List<Usuario> massaDeDados;

  @Dado("que eu esteja na tela inicial")
  public void que_eu_esteja_na_tela_inicial() {
    loginPage = Hooks.getLoginPage();
    homePage = Hooks.getHomePage();
    massaDeDados = Hooks.getMassaDeDados();
  }

  @Quando("realizar o login sem saldo")
  public void realizar_o_login_sem_saldo() {
    Usuario usuario = massaDeDados.get(0);

    loginPage.inserirEmail(usuario.getEmail());
    loginPage.inserirSenha(usuario.getSenha());
    loginPage.clickAcessar();
  }

  @Entao("o nome e a inicial deve ser o mesmo de cadastro")
  public void o_nome_e_a_inicial_deve_ser_o_mesmo_de_cadastro() {
    Usuario usuario = massaDeDados.get(0);

    String nomeAtual = homePage.obterTextName();
    String inicialAtual = homePage.obterTextNameInicial();
    String nomeExpected = usuario.getNome();
    String InicialExpected = String.valueOf(usuario.getNome().charAt(0)).toUpperCase();

    Assert.assertEquals(nomeAtual, nomeExpected);
    Assert.assertEquals(inicialAtual, InicialExpected);
  }

  @Entao("o numero da conta deve ser referente ao login realizado")
  public void o_numero_da_conta_deve_ser_referente_ao_login_realizado() {
    Usuario usuario = massaDeDados.get(0);

    String contaExpected = usuario.getConta();
    String contaAtual = homePage.obterTextAccountNumber();

    Assert.assertEquals(contaExpected, contaAtual);
  }

  @Entao("o saldo da conta deve ser zero")
  public void o_saldo_da_conta_deve_ser_zero() {
    String valorActual = homePage.obterTextBalance();

    Assert.assertEquals("0,00", valorActual);
  }

  @Quando("realizar o login com saldo")
  public void realizar_o_login_com_saldo() {
    Usuario usuario = massaDeDados.get(0);

    loginPage.inserirEmail(usuario.getEmail());
    loginPage.inserirSenha(usuario.getSenha());
    loginPage.clickAcessar();
  }

  @Entao("a conta deve possuir saldo")
  public void a_conta_deve_possuir_saldo() {
    String valorActual = homePage.obterTextBalance();

    Assert.assertEquals("1.000,00", valorActual);
  }

  @Quando("clicar em tranferencia")
  public void clicar_em_tranferencia() {
    homePage.clickTransferencia();
  }

  @Entao("a conta deve possuir a função de transferência")
  public void a_conta_deve_possuir_a_funcão_de_transferência() {
    Assert.assertTrue(homePage.getCurrentUrl("/transfer").contains("/transfer"));
  }

  @Quando("clicar em extrato")
  public void clicar_em_extrato() {
    homePage.clickExtrato();
  }

  @Entao("a conta deve possuir a função de extrato")
  public void a_conta_deve_possuir_a_funcão_de_extrato() {
    Assert.assertTrue(homePage.getCurrentUrl("/bank-statement").contains("/bank-statement"));
  }

  @Quando("clicar em sair")
  public void clicar_em_sair() {
    homePage.clickExit();
  }

  @Entao("a pagina deve voltar a tela inicial")
  public void a_pagina_deve_voltar_a_tela_inicial() {
    Assert.assertEquals("https://bugbank.netlify.app/", homePage.getCurrentUrl(""));
  }
}