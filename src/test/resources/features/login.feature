#language: pt
#encoding: UTF-8
@Login @I
Funcionalidade: testar login

  @CT001_ClicarApenasEmAcessar
  Esquema do Cenário: Clicar em acessar
    Dado que eu acesse a plataforma
    Quando clicar em acessar
    Entao as mensagens de email e senha devem ser apresentadas "<msg1>" e "<msg2>"
    
    Exemplos:
    |msg1               |msg2               |
    |É campo obrigatório|É campo obrigatório|
    
  @CT002_InserirEmailIncompleto 
  Esquema do Cenário: Inserir E-mail incompleto
    Dado que eu acesse a plataforma
    Quando inserir um email incompleto no login
    Entao a mensagem de email invalido deve ser apresentada "<msg1>"
    
    Exemplos:
    |msg1            |
    |Formato inválido|
    
  @CT003_InserirApenasSenha 
  Esquema do Cenário: Inserir apenas a senha
    Dado que eu acesse a plataforma
    Quando inserir a senha no login "<senha>"
    E clicar em acessar
    Entao a mensagem de login vazio deve ser apresentada "<msg1>"
    
    Exemplos:
    |msg1               |senha|
    |É campo obrigatório|123  |
    
  @CT004_InserirEmailESenhaNaoCadastrados 
  Esquema do Cenário: Tentar acessar sem cadastro
    Dado que eu acesse a plataforma
    Quando inserir email no login "<email>"
    E inserir a senha no login "<senha>"
    E clicar em acessar
    Entao a mensagem de alerta login não cadastrado deve ser apresentada "<msg1>"
    
    Exemplos:
    |msg1                                                                      |email            |senha|
    |Usuário ou senha inválido.\nTente novamente ou verifique suas informações!|exemplo@email.com|123  |

  @CT005_InserirApenasEmail 
  Esquema do Cenário: Inserir apenas E-mail
    Dado que eu acesse a plataforma
    Quando inserir email no login "<email>"
    E clicar em acessar
    Entao a mensagem de senha vazia deve ser apresentada "<msg1>"
    
    Exemplos:
    |email            |msg1               |
    |exemplo@email.com|É campo obrigatório|

  @CT006_InserirEmailESenhaCadastrados  @MassaComSaldo 
  Esquema do Cenário: Tentar acessar com cadastro
    Dado que eu acesse a plataforma
    Quando inserir email cadastrado no login
    E inserir a senha cadastrada no login
    E clicar em acessar
    Entao deve acessar a pagina home

    Exemplos:
    |   |   |
    |   |   |