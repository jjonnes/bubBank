package automacao.utils;

import org.openqa.selenium.WebElement;

public class StepsUtils {

  private static final String GREETING_PREFIX = "Olá ";
  private static final String GREETING_SUFFIX = ",";
  private static final String CURRENCY_PREFIX = "R$";

  private static final String[] NUMBERS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
  private static final String[] LOWERCASE_LETTERS = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
      "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
  private static final String[] UPPERCASE_LETTERS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
      "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
  private static final String[] SPECIAL_CHARACTERS = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+",
      "=", "[", "]", "{", "}", "|", "\\", ";", ":", "'", "\"", ",", ".", "<", ">", "?", "/" };
  private static final String[] WHITESPACE_CHARACTERS = { " ", "\t", "\n" };

  // Constantes para expressões regulares
  private static final String ACCOUNT_NUMBER_REGEX = ".*?(\\d+-\\d+).*";
  private static final String ACCOUNT_NUMBER_WITH_DIGIT_REGEX = ".*?\\d+-(\\d+).*";
  private static final String ACCOUNT_NUMBER_WITHOUT_DIGIT_REGEX = ".*?(\\d+)-\\d+.*";

  /**
   * Extrai nome de uma string pré-definida
   *
   * @param nome passado a String "Olá [Nome],"
   * @return [Nome]
   */
  public static String extraiNome(String nome) {
    return nome.substring(GREETING_PREFIX.length(), nome.length() - GREETING_SUFFIX.length()).trim();
  }

  /**
   * Extrai a inicial do nome de uma string
   *
   * @param nome passado a String "Olá [Nome],"
   * @return Inicial do [Nome] em maiúsculo
   */
  public static String extraiInicial(String nome) {
    String elemento = extraiNome(nome);
    return String.valueOf(elemento.charAt(0)).toUpperCase();
  }

  /**
   * Extrai o conteúdo que aparece após "R$" em uma string.
   *
   * @param input A string que contém "R$".
   * @return O conteúdo após "R$", sem espaços extras.
   */
  public static String extraiValor(String input) {
    int index = input.indexOf(CURRENCY_PREFIX);
    return input.substring(index + CURRENCY_PREFIX.length()).trim();
  }

  public static String extraiAcconutNumber(String input) {
    return input.replaceAll(ACCOUNT_NUMBER_REGEX, "$1");
  }

  public static String extraiAccountNumber(String input, boolean digito) {
    if (digito) {
      return input.replaceAll(ACCOUNT_NUMBER_WITH_DIGIT_REGEX, "$1");
    } else {
      return input.replaceAll(ACCOUNT_NUMBER_WITHOUT_DIGIT_REGEX, "$1");
    }
  }

  /**
   * Verifica se um elemento aceita apenas caracteres que correspondem ao regex
   * especificado
   * 
   * @param regex    padrão para validação
   * @param elemento elemento web a ser validado
   * @return true se o elemento aceita apenas caracteres que correspondem ao regex
   */
  public static boolean caracteresAceitos(String regex, WebElement elemento) {
    String valorOriginal = elemento.getAttribute("value");
    boolean apenasRegexAceito = true;

    for (String[] grupo : new String[][] { NUMBERS, LOWERCASE_LETTERS, UPPERCASE_LETTERS, SPECIAL_CHARACTERS,
        WHITESPACE_CHARACTERS }) {
      for (String caractere : grupo) {
        elemento.clear();
        elemento.sendKeys(caractere);
        String valorAtual = elemento.getAttribute("value");

        if (!valorAtual.isEmpty()) {
          if (!valorAtual.matches(regex)) {
            apenasRegexAceito = false;
            break;
          }
        }
      }
      if (!apenasRegexAceito)
        break;
    }

    elemento.clear();
    if (!valorOriginal.isEmpty()) {
      elemento.sendKeys(valorOriginal);
    }

    return apenasRegexAceito;
  }
}