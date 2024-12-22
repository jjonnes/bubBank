#language: pt
#encoding: UTF-8
@Home
Funcionalidade: testa página home

  @CT001_VerificarNomeEInicial @MassaComSaldo
  Cenário: Verificar nome e inicial
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    Entao o nome e a inicial deve ser o mesmo de cadastro
    
    Exemplos:
    |   |   |
    |   |   |

  @CT002_VerificarONumeroDaConta @MassaSemSaldo
  Cenário: Verificar o número da conta
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    Entao o numero da conta deve ser referente ao login realizado
    
    Exemplos:
    |   |   |
    |   |   |

  @CT003_VerificarContaSemSaldo @MassaSemSaldo
  Cenário: Verificar conta sem saldo
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    Entao o saldo da conta deve ser zero
    
    Exemplos:
    |   |   |
    |   |   |

  @CT004_VerificarContaComSaldo @MassaComSaldo
  Cenário: Verificar conta com saldo
    Dado que eu esteja na tela inicial
    Quando realizar o login com saldo
    Entao a conta deve possuir saldo
    
    Exemplos:
    |   |   |
    |   |   |

  @CT005_VerificarFuncaoTrasnferencia  @MassaSemSaldo
  Cenário: Verificar função de transferência
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    E clicar em tranferencia 
    Entao a conta deve possuir a função de transferência "<endPoint>"
    
    Exemplos:
    |endPoint |
    |/transfer|

  @CT006_VerificarFuncaoExtrato @MassaSemSaldo
  Cenário: Verificar função de extrato
    Dado que eu esteja na tela inicial
    Quando realizar o login sem saldo
    E clicar em extrato
    Entao a conta deve possuir a função de extrato "<endPoint>"
    
    Exemplos:
    |endPoint       |
    |/bank-statement|

  @CT007_VerificarBotaoSair @MassaSemSaldo
  Cenário: Verificar botão sair
    Dado que eu esteja na tela inicial
    E realizar o login sem saldo
    Quando clicar em sair
    Entao a pagina deve voltar a tela inicial
    
    Exemplos:
    |   |   |
    |   |   |




