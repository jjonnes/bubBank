#language: pt
#encoding: UTF-8
@I @Home 
Funcionalidade: testa página home

  @MassaComSaldo
  @CT001_VerificarNomeEInicial 
  Esquema do Cenário: Verificar nome e inicial
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    Entao o nome e a inicial deve ser o mesmo de cadastro
    
    Exemplos:
    |   |   |
    |   |   |

  @MassaSemSaldo
  @CT002_VerificarONumeroDaConta 
  Esquema do Cenário: Verificar o número da conta
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    Entao o numero da conta deve ser referente ao login realizado
    
    Exemplos:
    |   |   |
    |   |   |

  @MassaSemSaldo
  @CT003_VerificarContaSemSaldo
  Esquema do Cenário: Verificar conta sem saldo
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    Entao o saldo da conta deve ser zero
    
    Exemplos:
    |   |   |
    |   |   |

  @MassaComSaldo
  @CT004_VerificarContaComSaldo 
  Esquema do Cenário: Verificar conta com saldo
    Dado que eu esteja na tela inicial
    Quando realizar o login com saldo
    Entao a conta deve possuir saldo
    
    Exemplos:
    |   |   |
    |   |   |

  @MassaSemSaldo
  @CT005_VerificarFuncaoTrasnferencia 
  Esquema do Cenário: Verificar função de transferência
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    E clicar em tranferencia 
    Entao a conta deve possuir a função de transferência "<endPoint>"
    
    Exemplos:
    |endPoint |
    |/transfer|

  @MassaSemSaldo
  @CT006_VerificarFuncaoExtrato 
  Esquema do Cenário: Verificar função de extrato
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    E clicar em extrato
    Entao a conta deve possuir a função de extrato "<endPoint>"
    
    Exemplos:
    |endPoint       |
    |/bank-statement|

  @MassaSemSaldo
  @CT007_VerificarBotaoSair 
  Esquema do Cenário: Verificar botão sair
    Dado que eu esteja na tela inicial
    E realizar o login sem saldo
    Quando clicar em sair
    Entao a pagina deve voltar a tela inicial
    
    Exemplos:
    |   |   |
    |   |   |




