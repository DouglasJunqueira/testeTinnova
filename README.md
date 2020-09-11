# Iniciando

### Execução - Localhost

* Realize o build do projeto ou execute através de uma plataforma a sua escolha,
 (sugestão IntelliJ - mais fácil avaliar o projeto e execução)

* Inicie o projeto (caso linha de comando <b>java -jar "nome do projeto".jar</b>)
* Verifique problemas de conexão com banco, caso ocorra verifique a sessão BANCO
* Acesse [Aqui](http://localhost:8080/swagger-ui.html)
* Teste as funcionalidades

#### BANCO
A execução e funcionamento do mesmo depende da conexão com o banco.
A configuração do mesmo está no arquivo <i>application.properties</i>.
O banco utilizado deve ser MySql.

O projeto atual conta com acesso a uma base remota gratuita, que deve
atender o período de testes e validação. Caso necessário, por favor alterar
as configurações para um outro banco.

É importante apontar que o serviço não depende de scripts para geração das
entidades relacionadas, mas precisa da base (ex.: "djfortinnova") gerada.

##Ferramentas:

Para verificar informações sobre as ferramentas utilizadas, por favor acesse os links:

* [Documentação Oficial Gradle](https://docs.gradle.org)
* [Spring Boot & Gradle](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/gradle-plugin/reference/html/)
* [Criar uma iamgem OCI](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guias
Verifique também os guias para mudanças e manutenção:

* [RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content com Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Construindo um serviço REST com Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessando dados com JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessando dados com MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
