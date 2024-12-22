package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automacao.utils.PageUtils;
import automacao.utils.StepsUtils;

public class TransferenciaPage {

  private PageUtils pageUtils;

  public TransferenciaPage(WebDriver driver, Integer timeOut) {
    this.pageUtils = new PageUtils(driver, timeOut);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[1]/input")
  private WebElement accountNumber;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[2]/input")
  private WebElement digit;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[2]/input")
  private WebElement transferValue;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[3]/input")
  private WebElement description;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/button")
  private WebElement transferButton;

  @FindBy(xpath = "//*[@id=\"btnBack\"]")
  private WebElement backButton;

  @FindBy(xpath = "//*[@id=\"modalText\"]")
  private WebElement alert;

  @FindBy(xpath = "//*[@id=\"btnCloseModal\"]")
  private WebElement closeModalButton;

  @FindBy(xpath = "//*[@id='btnExit']")
  private WebElement exitButton;

  public void inserirNumeroConta(String numero) {
    String numeroConta = StepsUtils.extraiAccountNumber(numero, false);
    pageUtils.inserirElemento(accountNumber, numeroConta);
  }

  public boolean caracteresAceitosNumeroConta(String regex) {
    return StepsUtils.caracteresAceitos(regex, accountNumber);
  }

  public boolean caracteresAceitosDigitoConta(String regex) {
    return StepsUtils.caracteresAceitos(regex, digit);
  }

  public void inserirDigito(String numero) {
    String digitoConta = StepsUtils.extraiAccountNumber(numero, true);
    pageUtils.inserirElemento(digit, digitoConta);
  }

  public void inserirValorTransferencia(String valor) {
    pageUtils.inserirElemento(transferValue, valor);
  }

  public void inserirDescricao(String descricao) {
    pageUtils.inserirElemento(description, descricao);
  }

  public void clicarTransferir() {
    pageUtils.clicar(transferButton);
  }

  public void clicarVoltar() {
    pageUtils.clicar(backButton);
  }

  public String obterMensagemDeAlerta() {
    return pageUtils.obterTexto(alert);
  }

  public void fecharModal() {
    pageUtils.clicar(closeModalButton);
  }

  public void clickExit() {
    pageUtils.clicar(exitButton);
  }
}