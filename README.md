# backend-qualification

Um crud para gravar endereços através de uma API Rest.

## Iniciando
Leia as instruções a serguir para ter uma cópia do projeto e rodar em sua máquina local.

### Pré-requisitos

O que você precisa instalar para rodar essa aplicação:
- Banco de dados MySQL


### Instalando

#### Clone o projeto para uma pasta em sua máquina
```
git clone https://github.com/diegoqueres/backend-qualification.git
```

#### Crie um banco de dados no MYSQL para a aplicação usar
Por padrão, está configurado como: "backend_qualification"


#### Configure o arquivo application-dev.properties
Configure as propriedades de acordo com o banco MYSQL instalado.
```
#Database
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/backend_qualification
spring.datasource.username=root
spring.datasource.password=root
```

#### Cadastrando um novo usuário para usar a API
##### Cadastro do usuário
```
curl -X POST 'http://localhost:8080/users/signup' -i -H 'Content-Type: application/json' -d '{
    "email": "test.user@email.com.br",
    "password": "test.user.password"
}'
```

##### Recuperação de token
```
TOKEN_DATA=$(curl -X POST 'http://localhost:8080/login' -i -H 'Content-Type: application/json' -d '{
    "email": "test.user@email.com.br",
    "password": "test.user.password"
}')
```

##### Inserindo o token no header para rodar a operação desejada na api
```
curl --location --request GET 'http://localhost:8080/api/v1/states' \
--header 'Content-Type: application/json' \
--header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0LnVzZXJAZW1haWwuY29tLmJyIiwiZXhwIjoxNTkxMDMxMTY3fQ.IhSTk9eQaIAw2QL419uencF8wG4AhCLC8h6dNuccuImQskXtRTAh36zdvVcFmPrSwvoBSv0LTr0npH9_Vk-yOg'
```