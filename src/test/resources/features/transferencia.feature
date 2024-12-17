#language: pt
#encoding: UTF-8
@Transferencia
Funcionalidade: testar tranferencia

  
  @CT001_TransferenciaContaInvalida @MassaComSaldo 
  Esquema do Cenário: Tentativa de transferência para conta inválida
    Dado que eu estou na página de transferência
    Quando inserir o número da conta inexistente "<conta>" e o dígito "<conta>"
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ver a mensagem "<msg1>"

    Exemplos:
    |conta   |descrição|valor|msg1                         |
    |123456-0|Pagamento|100  |Conta inválida ou inexistente|

 
  @CT002_TransferenciaSaldoInsuficiente @MassaComSaldo
  Esquema do Cenário: Tentativa de transferência com saldo insuficiente
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ver a mensagem "<msg1>"

    Exemplos:
    |descrição|valor|msg1                                             |
    |Pagamento|10000|Você não tem saldo suficiente para essa transação|

 
  @CT003_TransferenciaValorInvalido @MassaComSaldo
  Esquema do Cenário: Tentativa de transferência com valor inválido
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ver a mensagem "<msg1>"

    Exemplos:
    |descrição|valor|msg1                                             |
    |Pagamento|-50  |Valor da transferência não pode ser 0 ou negativo|

 
  @CT004_ModalDeTransferenciaRealizadaComSucesso @MassaComSaldo
  Esquema do Cenário: Modal de transferência realizada com sucesso
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ver a mensagem "<msg1>"

    Exemplos:
    |descrição|valor|msg1                               |
    |Pagamento|100  |Transferência realizada com sucesso|

 
  @CT005_TransferenciaRealizadaComSucessoComReducaoDeSaldo @MassaComSaldo
  Esquema do Cenário: Transferência realizada com sucesso com redução de saldo
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ter dedução no saldo de "<valor>"

    Exemplos:
    |descrição|valor|msg1                               |
    |Pagamento|100  |Transferência realizada com sucesso|

 
  @CT006_RedirecionamentoAposTransferenciaRealizadaComSucesso @MassaComSaldo
  Esquema do Cenário: Redirecionamento após transferência realizada com sucesso
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ser redirecionado para a página de extrato "<endPoint>"

    Exemplos:
    |descrição|valor|endPoint       |
    |Pagamento|100  |/bank-statement|

 
  @CT007_CampoDescricaoObrigatorio @MassaComSaldo
  Esquema do Cenário: Campo descrição é obrigatório
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ver a mensagem "<msg1>"

    Exemplos:
    |valor |msg1                         |
    |100   |Campo descrição é obrigatório|

  
  @CT008_TransferenciaRealizadaComSucessoComCreditoNaConta @MassaComSaldo
  Esquema do Cenário: Transferência realizada com sucesso com crédito na conta
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o campo descrição com "<descrição>"
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao a conta destino deve receber "<valor>" com a descrição "<descrição>"

    Exemplos:
    |valor|descrição|
    |100  |Pagamento|

  
  @CT009_ValidacaoCaracteresNumeroConta @MassaComSaldo
  Esquema do Cenário: Validação de caracteres aceitos no número da conta
    Dado que eu estou na página de transferência
    Entao validar que o campo número da conta aceita apenas o padrão "<regex>"

    Exemplos:
    |regex    |
    |^[0-9]+$ |

  @CT009_ValidacaoCaracteresNumeroConta @MassaComSaldo
  Esquema do Cenário: Validação de caracteres aceitos no número da conta
    Dado que eu estou na página de transferência
    Entao validar que o campo dígito aceita apenas o padrão "<regex>"

    Exemplos:
    |regex    |
    |^[0-9]+$ |
