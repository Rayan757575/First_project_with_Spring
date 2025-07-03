# Anime API

API REST desenvolvida com Spring Boot para gerenciamento de entidades `Anime`.

---

## 📦 Tecnologias utilizadas

- Java 21  
- Spring Boot 3.5  
- Spring Data JPA  
- MySQL  
- Docker & Docker Compose  
- MapStruct  
- Lombok  
- Maven  

---

## 🚀 Como rodar o projeto

### 1. Clone o repositório

Execute os comandos abaixo para clonar este repositório localmente:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
````

### 2. Suba o banco de dados com Docker Compose

Certifique-se de ter o Docker instalado e em execução. Em seguida, inicie o banco de dados MySQL com:

```bash
docker-compose up -d
```

O container estará disponível em `localhost:3306` com os seguintes dados:

* **Usuário:** `root`
* **Senha:** `root`

Após o container estar em funcionamento, crie manualmente o banco de dados `anime` com o seguinte comando SQL:

```sql
CREATE DATABASE anime;
```

Você pode executar esse comando em qualquer cliente MySQL, como MySQL Workbench, DBeaver ou via terminal:

```bash
mysql -u root -p
```

### 3. Configure o `application.properties`

No arquivo `src/main/resources/application.properties`, confirme se a conexão está configurada corretamente:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/anime?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### 4. Compile o projeto

Execute o seguinte comando para compilar o projeto:

```bash
./mvnw clean install
```

Se estiver usando Windows:

```bash
mvnw.cmd clean install
```

### 5. Execute a aplicação

Para iniciar o servidor Spring Boot, utilize:

```bash
./mvnw spring-boot:run
```

Ou, no Windows:

```bash
mvnw.cmd spring-boot:run
```

A API estará acessível em:

```
http://localhost:8080
```

---

## 📬 Testes com Postman

Você pode testar os endpoints utilizando o Postman ou Insomnia.

Exemplo de requisição `POST` para o endpoint `/animes`:

* **URL:**

```bash
POST http://localhost:8080/animes
```

* **Body (JSON):**

```json
{
  "name": "Naruto"
}
```

---

## 🔧 Geração de mapeamentos com MapStruct

Para que o MapStruct funcione corretamente, você precisa ativar o processamento de anotações no IntelliJ IDEA:

1. Acesse `File > Settings`
2. Vá para `Build, Execution, Deployment > Compiler > Annotation Processors`
3. Marque a opção **"Enable annotation processing"**
4. Clique em **Apply** e depois **OK**

Com isso, o MapStruct poderá gerar automaticamente os mapeadores `*Impl` durante a compilação.

---

## ✍️ Autor

Rayan Cata Preta

---
