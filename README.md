# Api-Medical-Clinic

Este documento apresenta uma visão geral do projeto, descrevendo as etapas de desenvolvimento e as principais funcionalidades implementadas. O objetivo é fornecer informações detalhadas sobre a criação de uma API RESTful com Spring Boot e Spring Security, destacando as boas práticas aplicadas e os recursos utilizados.

# Panorama Geral do Projeto:

O projeto consiste na criação de uma API RESTful utilizando o framework Spring Boot. Ele aborda o desenvolvimento de um sistema de autenticação e autorização stateless utilizando Spring Security em conjunto com JSON Web Tokens (JWT). Além disso, inclui a definição de entidades de usuário, como Médico e Paciente, para permitir diferentes níveis de acesso com base nos perfis dos usuários.

# Funcionalidades Implementadas:

* Autenticação e Autorização Stateless: Utilização do Spring Security com JWT para implementar um sistema de autenticação e autorização stateless.
* Definição de Entidades de Usuário: Implementação das entidades Médico e Paciente para representar diferentes tipos de usuários na aplicação.
* Boas Práticas na API: Aplicação de boas práticas no desenvolvimento da API, incluindo o uso correto do Protocolo HTTP e a implementação de mecanismos de autenticação seguros.
* Princípios SOLID: Isolamento dos códigos de regras de negócio seguindo os princípios do SOLID para garantir uma arquitetura flexível e de fácil manutenção.
* Documentação da API: Utilização do padrão OpenAPI para documentar todos os endpoints disponíveis na API, fornecendo uma descrição clara e precisa das funcionalidades oferecidas.
* Testes Automatizados: Escrita de testes automatizados, abrangendo desde testes unitários até testes de integração, para garantir a qualidade e a confiabilidade do código desenvolvido.
* Build da Aplicação: Realização do build da aplicação de forma eficiente, preparando-a para o deploy em diferentes ambientes.
* Utilização de Variáveis de Ambiente: Configuração flexível da aplicação utilizando variáveis de ambiente, facilitando o gerenciamento das configurações em diferentes cenários de deploy.

# Pré-requisitos:

* Java 17 ou superior
* Spring Boot
* Maven
* MySQL
* Insomnia

# Rodando o projeto:

1 - Clone o repositório:
```
  https://github.com/daniel2dfla/Api-Medical-Clinic.git
```
2 - Navegue até o diretório do projeto:
```
  cd Api-Medical-Clinic
```
3 - Execute o aplicativo usando Maven:

```
  mvn spring-boot:run
```
4 - O aplicativo estará disponível em:
```
  http://localhost:8080.
```

5 - Acessando a Documentação do Swagger:
```
  http://localhost:8080/swagger-ui/index.html#/
```

# Endpoints:
* Login
  ![login](https://github.com/daniel2dfla/Api-Medical-Clinic/assets/98188853/8487848e-6ccf-4fe2-ad34-e310ca8f794e)

* Pacientes
![Patient controler](https://github.com/daniel2dfla/Api-Medical-Clinic/assets/98188853/36456067-35b7-461b-8584-5a8ee29003db)
![Put patients](https://github.com/daniel2dfla/Api-Medical-Clinic/assets/98188853/8bc618e4-d6c3-4c87-ad40-355a40901cb0)


* Médicos
![Doctor controler](https://github.com/daniel2dfla/Api-Medical-Clinic/assets/98188853/4a5178bd-1c99-4c69-b3ca-663df1baab75)
