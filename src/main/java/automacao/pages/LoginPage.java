package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automacao.utils.PageUtils;

public class LoginPage {

  private PageUtils pageUtils;

  public LoginPage(WebDriver driver, Integer timeOut) {
    this.pageUtils = new PageUtils(driver, timeOut);
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = "input[name='email']")
  private WebElement emailField;

  @FindBy(css = "form div:nth-child(1) p")
  private WebElement emailFieldSpan;

  @FindBy(css = "input[name='password']")
  private WebElement senhaField;

  @FindBy(css = "form div:nth-child(2) p")
  private WebElement senhaFieldSpan;

  @FindBy(css = "button[type='submit']")
  private WebElement acessarButton;

  @FindBy(css = "button.style__ContainerButton-sc-1wsixal-0")
  private WebElement registrarButton;

  @FindBy(css = "#modalText")
  private WebElement usuarioOuSenhaAlert;

  public void inserirEmail(String email) {
    pageUtils.inserirElemento(emailField, email);
  }

  public String obterTextoEmailFieldSpan() {
    return pageUtils.obterTexto(emailFieldSpan);
  }

  public void inserirSenha(String senha) {
    pageUtils.inserirElemento(senhaField, senha);
  }

  public String obterTextoSenhaFieldSpan() {
    return pageUtils.obterTexto(senhaFieldSpan);
  }

  public void clickAcessar() {
    pageUtils.clicar(acessarButton);
  }

  public void clickRegistrar() {
    pageUtils.clicar(registrarButton);
  }

  public String obterTextoDeAlertaUsuarioOuSenhaInvalidos() {
    return pageUtils.obterTexto(usuarioOuSenhaAlert);
  }
}
