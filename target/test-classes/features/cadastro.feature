#language: pt
#encoding: UTF-8
@Cadastro
Funcionalidade: Testar cadastro

	@CT001_ClicarEmCadastrarComCamposVazios
	Esquema do Cenário: Clicar em cadastrar com todos os campos vazios
		Dado que eu acesse a tela de cadastro
		Quando clicar em cadastrar
		Entao as mensagens de email senha e confirmar senha devem ser apresentadas "<msg1>", "<msg2>", "<msg3>", "<msg4>"
		
		Exemplos:
		|msg1                   |msg2                    |msg3                    |msg4                              |
		|Nome não pode ser vazio|Email não pode ser vazio|Senha não pode ser vazio|Confirmar senha não pode ser vazio|

	@CT002_InserirEmailIncompleto
	Esquema do Cenário: Inserir E-mail incompleto
		Dado que eu acesse a tela de cadastro
		Quando inserir um e-mail incompleto no cadastro
		Entao a mensagem em cadastro de email invalido deve ser apresentada "<msg1>"
		
		Exemplos:
		|msg1            |
		|Formato inválido|

	@CT003_InserirApenasSenha
	Esquema do Cenário: Inserir apenas a senha sem a confirmação
		Dado que eu acesse a tela de cadastro
		Quando inserir senha no cadastro "<senha>"
		E inserir nome no cadastro "<nome>"
		E inserir email no cadastro "<email>"
		E clicar em cadastrar
		Entao a mensagem deve ser apresentada em confirmar senha "<msg1>"
		
		Exemplos:
		|msg1               |nome|email            |senha|
		|É campo obrigatório|nome|exemplo@email.com|123  |

	@CT004_EfetuarCadastro
	Esquema do Cenário: Realizar cadastro
		Dado que eu acesse a tela de cadastro
		Quando inserir email no cadastro "<email>"
		E inserir senha no cadastro "<senha>"
		E inserir nome no cadastro "<nome>"
		E inserir confirmação da senha "<senha>"
		E clicar em cadastrar
		Entao a mensagem de alerta de cadastro deve ser apresentada "<msg1>"
		
		Exemplos:
		|msg1                          |email            |senha|nome|
		|A conta foi criada com sucesso|exemplo@email.com|123  |nome|
	
	@CT005_EfetuarCadastroComSaldo
		Esquema do Cenário: Realizar cadastro com valor de saldo
		Dado que eu acesse a tela de cadastro
		Quando inserir email no cadastro "<email>"
		E inserir senha no cadastro "<senha>"
		E inserir nome no cadastro "<nome>"
		E inserir confirmação da senha "<senha>"
		E marcar a opção conta com saldo
		E clicar em cadastrar
		Entao a mensagem de alerta de cadastro deve ser apresentada "<msg1>"

		Exemplos:
		|msg1                          |email            |senha|nome|
		|A conta foi criada com sucesso|exemplo@email.com|123  |nome|
