Teste prático API

Autor: Pedro Araújo Júnior


INSTRUÇÔES

1 - Como compilar e executar a aplicação:

	Para compilar a aplicação
	comando: mvn clean install

	Para executar a aplicação
	comando: mvn exec:java
		     mvn spring-boot:run

-------------------------------------------------------------------------------------------------------------

2 - Exemplos de cada chamada da api:

	A api implementada possui 14 chamadas, segue abaixo os exemplos e métodos http de cada uma:

	-----------------------------------------------------------------------------------

	HttpMethod: GET

	Recupera todos os produtos registrados no banco incluindo relacionamentos
	http://localhost:8080/api/produto

	Recupera todos os produtos registrados no banco excluindo relacionamentos
	http://localhost:8080/api/produto/simple

	Recupera todos os produtos filhos do produto de id igual a 1
	http://localhost:8080/api/produto/parent/1

	Recupera o produto de id igual a 1 incluindo relacionamentos
	http://localhost:8080/api/produto/1

	Recupera o produto de id igual a 1 excluindo relacionamentos
	http://localhost:8080/api/produto/simple/1

	Recupera todas as imagens registradas no banco
	http://localhost:8080/api/imagem/

	Recupera todas as imagens do produto com id igual a 1
	http://localhost:8080/api/imagem/produto/1

	As chamadas implementadas na api podem ser visualizadas pela requisição
	http://localhost:8080/api/application.wadl

	-----------------------------------------------------------------------------------

	HttpMethod: POST

	Insere o produto enviado no corpo da requisição em formato json
	http://localhost:8080/api/produto/

	Insere a imagem enviada no corpo da requisição em formato json
	http://localhost:8080/api/imagem/

	-----------------------------------------------------------------------------------
	
	HttpMethod: PUT

	Atualiza o produto enviado no corpo da requisição em formato json
	http://localhost:8080/api/produto/

	Atualiza a imagem enviada no corpo da requisição em formato json
	http://localhost:8080/api/imagem/

	-----------------------------------------------------------------------------------

	HttpMethod: DELETE

	Remove o produto de id 1 do banco de dados
	http://localhost:8080/api/produto/1

	Remove a imagem de id 1 do banco de dados
	http://localhost:8080/api/imagem/1

-------------------------------------------------------------------------------------------------------------	
	
3 - Como executar a suíte de testes automatizados

	Foram implementados 13 testes, um para cada chamada da api, e podem ser executados pelo maven com o comando: mvn test

-------------------------------------------------------------------------------------------------------------	

Observação:

	Para efeito de testes manuais foram mapeadas mais duas requisições que inserem registros no banco de dados

	http://localhost:8080/produtos - Insere 5 produtos a cada requisição
	http://localhost:8080/imagens  - Insere 10 imagens a cada requisição

	O resultado de ambas requisições é uma pagina html com o toString dos objetos existentes no banco	

