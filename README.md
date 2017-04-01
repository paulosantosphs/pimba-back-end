## Pimba back-end projeto principal
Webservice para aplicação mobile e aplicação web

### Boas práticas de projeto
Recomendado que as estruturas, váriaveis e métodos sejam denominados em inglês, para facilitar a integração com o framework.  
A estrutura de projeto está organizada em MVC, onde cada package contém tanto o model, quanto o view e o controller.  
Ex:
```
projeto/
	login/
		Login.java
		LoginResponse.java
		LoginRepository.java
		LoginController.java
		LoginService.java
```
### Uso
- Rode a aplicação e acesse http://localhost:8080

### Compilação e execução
Abra a pasta do projeto e execute:

	$ mvn package compile

### Atualizacão do repositório
Para adicionar os arquivos ao repositório:

	$ git add .
	$ git commit -m "Mensagem informando as alteracoes que voce fez"
	$ git push origin master

Para baixar as atualizações do projeto:

	$ git pull origin master