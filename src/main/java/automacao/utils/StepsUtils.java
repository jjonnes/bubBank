package automacao.utils;

public class StepsUtils {

  /**
   * Extrai nome de uma string pré-definida
   *
   * @param nome passado a String "Olá [Nome],"
   * @return [Nome]
   */
  public static String extraiNome(String nome) {
    return nome.substring(4, nome.length() - 1).trim();
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
    int index = input.indexOf("R$");

    return input.substring(index + 2).trim();
  }
}
