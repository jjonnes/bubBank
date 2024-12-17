#language: pt
#encoding: UTF-8
@Extrato
Funcionalidade: Testar extrato

  @CT001_ValidarSaldoDisponivel @MassaSemSaldo
  Cenário: Validar exibição do saldo disponível
    Dado que eu estou na página de extrato
    Então devo visualizar o saldo disponível atual

		Exemplos:
		| |
		| |

  @CT002_ValidarTransacaoAberturaConta @MassaSemSaldo
  Cenário: Validar transação de abertura de conta
    Dado que eu estou na página de extrato
    Então devo visualizar a transação com a descricao "<descricao>"nnnnnnnnnnnnn

		Exemplos:
		| descricao |
		| Abertura de conta |


  @CT003_ValidarTransferenciaEnviada @MassaComSaldo
  Cenário: Validar transferência enviada
    Dado que eu estou na página de extrato
    Quando eu realizar uma transferência de "<valor>" com a descrição "<descricao>"
    Então devo visualizar a transação em vermelho com a descricao "<descricao>" e o valor negativo

		Exemplos:
		|valor|descricao|
		|100  |pagamento|

  @CT004_ValidarTransferenciaRecebida @MassaComSaldo
  Cenário: Validar transferência recebida
    Dado que eu estou na página de extrato
    Quando eu receber uma transferência de "<valor>" reais com a descrição "<descricao>"
    Então devo visualizar a transação em verde

		Exemplos:
		|valor|descricao  |
		|50   |Recebimento|

  @CT005_ValidarTransacaoSemComentario @MassaComSaldo
  Cenário: Validar transação sem comentário
    Dado que eu estou na página de extrato
    Quando eu realizar uma transferência de "<valor>" reais sem descrição
    Então devo visualizar a transação em vermelho com a descricao -

		Exemplos:
		|valor|
		|75   |
