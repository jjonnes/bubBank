package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.utils.PageUtils;
import automacao.utils.StepsUtils;

import java.time.Duration;

public class CadastroPage {

  @SuppressWarnings("unused")
  private WebDriverWait wait;
  private PageUtils pageUtils;

  public CadastroPage(WebDriver driver) {
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.pageUtils = new PageUtils(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]")
  private WebElement registrarButton;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input")
  private WebElement emailField;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/p")
  private WebElement emailFieldSpan;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[3]/input")
  private WebElement nomeField;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[3]/p")
  private WebElement nomeFieldSpan;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input")
  private WebElement senhaField;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/p")
  private WebElement senhaFieldSpan;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[5]/div/input")
  private WebElement confirmarSenhaField;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[5]/div/p")
  private WebElement confirmarSenhaFieldSpan;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[6]/label")
  private WebElement contaComSaldoToggle;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/button")
  private WebElement cadastrarButton;

  @FindBy(xpath = "//*[@id=\"modalText\"]")
  private WebElement alertaMessage;

  @FindBy(xpath = "//*[@id=\"btnCloseModal\"]")
  private WebElement closeModalButton;

  public void inserirEmail(String email) {
    pageUtils.inserirElemento(emailField, email);
  }

  public void inserirNome(String nome) {
    pageUtils.inserirElemento(nomeField, nome);
  }

  public void inserirSenha(String senha) {
    pageUtils.inserirElemento(senhaField, senha);
  }

  public void inserirConfirmarSenha(String senha) {
    pageUtils.inserirElemento(confirmarSenhaField, senha);
  }

  public void clickContaComSaldo() {
    pageUtils.clicar(contaComSaldoToggle);
  }

  public void clickCadastrar() {
    pageUtils.clicar(cadastrarButton);
  }

  public void clickRegistrar() {
    pageUtils.clicar(registrarButton);
  }

  public String obterTextoEmailFieldSpan() {
    return pageUtils.obterTexto(emailFieldSpan);
  }

  public String obterTextoNomeFieldSpan() {
    return pageUtils.obterTexto(nomeFieldSpan);
  }

  public String obterTextoSenhaFieldSpan() {
    return pageUtils.obterTexto(senhaFieldSpan);
  }

  public String obterTextoConfirmarSenhaFieldSpan() {
    return pageUtils.obterTexto(confirmarSenhaFieldSpan);
  }

  public String obterTextoAlerta() {
    return pageUtils.obterTexto(alertaMessage);
  }

  public String obterNumeroDaConta() {
    String account = pageUtils.obterTexto(alertaMessage);
    return StepsUtils.extraiAcconutNumber(account);
  }

  public void clicarFechar() {
    pageUtils.clicar(closeModalButton);
  }
}
