<img src="assets\Logo.png" alt="Logo Facilita Ai" width="200">

> O projeto contempla uma API com serviços CRUD para as principais entidades
> do site Facilita Ai, permitindo tratar requisições de forma organizada e eficiente,
> e realizando a persistência em banco de dados.

## 🚀 Tecnologias Utilizadas

- [Java 21 - SpringBoot](https://docs.spring.io/spring-boot/index.html)
- [PostgreSQL]
- Docker
- JWT
- [Swagger](https://swagger.io/) para documentação da API 

---

## 📦 Instalação e Execução Local

Obs.: É necessário possuir JDK 21 e Java na máquina.

### 1. Clone o repositório
```bash
git clone https://github.com/PET-Sistemas/facilita-ai-project.git
cd facilita-ai-project
```
### 2. Configure as variáveis de ambiente
O projeto possui dois ambientes, um de produção e outro de desenvolvimento
ambos são configurados na classe principal do projeto (GetpetApplication.java),
na linha de código: System.setProperty(Abst...);

#### 2.1. Ambiente de desenvolvimento:
O ambiente de desenvolvimento é destinado aos desenvolvedores, facilitando a execução do projeto.
```java
@SpringBootApplication
public class GetpetApplication {
  ...
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "");
  ...
```
#### 2.2. Ambiente de produção:
O ambiente de produção tem o objetivo de subir tanto a aplicação quanto o banco de dados juntos no mesmo ambiente
utilizando docker, quando a aplicação estiver em produção.
```java
@SpringBootApplication
public class GetpetApplication {
  ...
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "production");
  ...
```
## 3. Como executar o projeto em desenvolvimento
1. execute o comando para subir o banco de dados: ```docker-compose up db```
2. execute a classe principal do projeto em GetpetApplication.java.

## 4. Como executar o projeto em produção
1. execute o comando para subir a api e o banco de dados: ``` docker-compose up OU  docker-compose up api db```

## 5. Para acessar a documentação:
1. Com a aplicação rodando, abra o navegador e entre no seguinte link : ``` (http://localhost:8080/swagger-ui/index.html) ```
