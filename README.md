# API Dealership

Repositório para estudo de criação de API Rest baseando-se num sistema de vendas de
veículos por concessionárias.

Esta API foi desenvolvida usando **Java** com **Spring Boot** e **Hibernate ORM**.
Ela permite o gerenciamento de concessionárias, veículos, vendas e inventários.
A API permite operações de CRUD para as entidades **User**, **Dealership**,
**Vehicle**, **Sale** e **Inventory**.
Atualmente, utilizo o banco de dados H2 para fins de desenvolvimento.

## Tecnologias Utilizadas

- <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/java.png" width="30" height="30" style="vertical-align: middle;"> **Java 21**
- <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/spring_boot.png" width="30" height="30" style="vertical-align: middle;"> **Spring Boot**
- <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/hibernate.png" width="30" height="30" style="vertical-align: middle;"> **Hibernate ORM**
- <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/maven.png" width="30" height="30" style="vertical-align: middle;"> **Maven**
- <img src="https://user-images.githubusercontent.com/140953/31318993-873f0796-ac5b-11e7-91b7-3c55f0e704a7.png" width="30" height="30" style="vertical-align: middle;"> **H2 Database**

## Instalação

Para rodar a API localmente, siga os passos abaixo:

1. Certifique-se de ter o **Java 21** e o **Maven** instalados em seu computador.

2. Clone o repositório:
    ``` 
    git clone https://github.com/scostavinicius/dealership.git 
    ```

3. Navegue até o diretório do projeto.
    ```
    cd dealership
    ```

4. Para compilar e rodar a aplicação, execute o seguinte comando:
    ``` 
    mvn spring-boot:run 
    ```

5. A aplicação estará rodando em `http://localhost:8080`.

## Endpoints

### Users

- **GET /users**: Retorna todos os usuários.
- **GET /users/{id}**: Retorna um usuário pelo ID.
- **POST /users**: Cria um novo usuário.
- **PUT /users/{id}**: Atualiza os dados de um usuário pelo ID.
- **DELETE /users/{id}**: Deleta um usuário pelo ID.

### Vehicles

- **GET /vehicles**: Retorna todos os veículos.
- **GET /vehicles/{id}**: Retorna um veículo pelo ID.
- **POST /vehicles**: Cria um novo veículo.
- **PUT /vehicles/{id}**: Atualiza os dados de um veículo pelo ID.
- **DELETE /vehicles/{id}**: Deleta um veículo pelo ID.

### Dealerships

- **GET /dealerships**: Retorna todas as concessionárias.
- **GET /dealerships/{id}**: Retorna uma concessionária pelo ID.
- **POST /dealerships**: Cria uma nova concessionária.
- **PUT /dealerships/{id}**: Atualiza os dados de uma concessionária pelo ID.
- **DELETE /dealerships/{id}**: Deleta uma concessionária pelo ID.

### Inventories

- **GET /inventories**: Retorna todos os inventários.
- **GET /inventories/{dealershipId}/{vehicleId}**: Retorna um estoque pelo ID da concessionária e ID do veículo (chave
  composta).
- **PATCH /inventories/{dealershipId}/{vehicleId}/add**: Adiciona um veículo ao estoque.
- **PATCH /inventories/{dealershipId}/{vehicleId}/remove**: Remove um veículo do estoque.

### Sales

- **GET /sales**: Retorna todas as vendas.
- **GET /sales/{id}**: Retorna uma venda pelo ID.
- **POST /sales**: Cria uma nova venda.
- **DELETE /sales/{id}**: Deleta uma venda pelo ID.