#language: pt
#encoding: UTF-8
@I @Transferencia
Funcionalidade: testar tranferencia

  @MassaComSaldo 
  @CT001_TransferenciaContaInvalida
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

  @MassaComSaldo
  @CT002_TransferenciaSaldoInsuficiente 
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

  @MassaComSaldo
  @CT003_TransferenciaValorInvalido 
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

  @MassaComSaldo
  @CT004_ModalDeTransferenciaRealizadaComSucesso 
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

  @MassaComSaldo
  @CT005_TransferenciaRealizadaComSucessoComReducaoDeSaldo
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

  @MassaComSaldo
  @CT006_RedirecionamentoAposTransferenciaRealizadaComSucesso 
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

  @MassaComSaldo
  @CT007_CampoDescricaoObrigatorio 
  Esquema do Cenário: Campo descrição é obrigatório
    Dado que eu estou na página de transferência
    Quando inserir o número da conta
    E inserir o valor da transferência com "<valor>"
    E clicar no botão Tranferir agora
    Entao eu devo ver a mensagem "<msg1>"

    Exemplos:
    |valor |msg1                         |
    |100   |Campo descrição é obrigatório|