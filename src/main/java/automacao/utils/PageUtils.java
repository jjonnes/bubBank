package automacao.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class PageUtils {

  private WebDriverWait wait;

  public PageUtils(WebDriver driver, Integer timeOut) {
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
  }

  public void inserirElemento(WebElement elemento, String arg1) {
    try {
      wait.until(ExpectedConditions.visibilityOf(elemento));
      elemento.clear();
      elemento.sendKeys(arg1);
    } catch (TimeoutException e) {
      System.err.println("Erro: O tempo de espera para o elemento expirou ao tentar inserir texto. " + e.getMessage());
      throw e;
    } catch (Exception e) {
      System.err.println("Erro inesperado ao tentar inserir texto: " + e.getMessage());
      throw e;
    }
  }

  public String obterTexto(WebElement elemento) {
    try {
      wait.until(ExpectedConditions.visibilityOf(elemento));
      return elemento.getText();
    } catch (TimeoutException e) {
      System.err.println("Erro: O tempo de espera para o elemento expirou ao tentar obter texto. " + e.getMessage());
      throw e;
    } catch (Exception e) {
      System.err.println("Erro inesperado ao tentar obter texto: " + e.getMessage());
      throw e;
    }
  }

  public void clicar(WebElement elemento) {
    try {
      wait.until(ExpectedConditions.elementToBeClickable(elemento));
      elemento.click();
    } catch (TimeoutException e) {
      System.err.println("Erro: O tempo de espera para o elemento expirou ao tentar clicar. " + e.getMessage());
      throw e;
    } catch (Exception e) {
      System.err.println("Erro inesperado ao tentar clicar: " + e.getMessage());
      throw e;
    }
  }
}