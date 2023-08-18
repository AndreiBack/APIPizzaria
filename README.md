Instruções de Uso das Controllers no Postman
Aqui estão as instruções de uso para cada uma das suas controllers no Postman. Certifique-se de que o seu servidor Spring Boot esteja em execução antes de executar essas solicitações.

Cliente Controller
GET /clientes/{id}
Descrição: Recupera informações de um cliente específico.
Exemplo de URL: http://localhost:8080/clientes/{id}
Método: GET
GET /clientes
Descrição: Recupera uma lista de todos os clientes cadastrados.
Exemplo de URL: http://localhost:8080/clientes
Método: GET
POST /clientes
Descrição: Cria um novo cliente.
Exemplo de URL: http://localhost:8080/clientes
Método: POST
Exemplo de corpo JSON:

{
    "nome": "João da Silva",
    "idade": 30,
    "cpf": "123.456.789-00",
    "email": "joao@example.com",
    "senha": "secretpassword",
    "telefone": "(11) 98765-4321"
}
PUT /clientes/{id}
Descrição: Atualiza informações de um cliente existente.
Exemplo de URL: http://localhost:8080/clientes/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "nome": "João da Silva",
    "idade": 31,
    "cpf": "123.456.789-00",
    "email": "joao@example.com",
    "senha": "newpassword",
    "telefone": "(11) 98765-4321"
}
DELETE /clientes/{id}
Descrição: Exclui um cliente específico.
Exemplo de URL: http://localhost:8080/clientes/{id}
Método: DELETE
Endereço Controller
GET /enderecos/{id}
Descrição: Recupera informações de um endereço específico.
Exemplo de URL: http://localhost:8080/enderecos/{id}
Método: GET
GET /enderecos
Descrição: Recupera uma lista de todos os endereços cadastrados.
Exemplo de URL: http://localhost:8080/enderecos
Método: GET
POST /enderecos
Descrição: Cria um novo endereço.
Exemplo de URL: http://localhost:8080/enderecos
Método: POST
Exemplo de corpo JSON:

{
    "cep": "12345-678",
    "rua": "Rua das Flores",
    "bairro": "Centro",
    "numero": 123
}
PUT /enderecos/{id}
Descrição: Atualiza informações de um endereço existente.
Exemplo de URL: http://localhost:8080/enderecos/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "cep": "12345-678",
    "rua": "Rua das Flores",
    "bairro": "Centro",
    "numero": 124
}
DELETE /enderecos/{id}
Descrição: Exclui um endereço específico.
Exemplo de URL: http://localhost:8080/enderecos/{id}
Método: DELETE
Funcionário Controller
GET /funcionarios
Descrição: Recupera uma lista de todos os funcionários cadastrados.
Exemplo de URL: http://localhost:8080/funcionarios
Método: GET
POST /funcionarios
Descrição: Cria um novo funcionário.
Exemplo de URL: http://localhost:8080/funcionarios
Método: POST
Exemplo de corpo JSON:

{
    "nome": "Maria Oliveira",
    "idade": 25,
    "cpf": "987.654.321-00",
    "email": "maria@example.com",
    "senha": "secretpassword",
    "telefone": "(11) 98765-4321"
}
PUT /funcionarios/{id}
Descrição: Atualiza informações de um funcionário existente.
Exemplo de URL: http://localhost:8080/funcionarios/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "nome": "Maria Oliveira",
    "idade": 26,
    "cpf": "987.654.321-00",
    "email": "maria@example.com",
    "senha": "newpassword",
    "telefone": "(11) 98765-4321"
}
DELETE /funcionarios/{id}
Descrição: Exclui um funcionário específico.
Exemplo de URL: http://localhost:8080/funcionarios/{id}
Método: DELETE
Pedido Controller
GET /pedidos
Descrição: Recupera uma lista de todos os pedidos cadastrados.
Exemplo de URL: http://localhost:8080/pedidos
Método: GET
POST /pedidos
Descrição: Cria um novo pedido.
Exemplo de URL: http://localhost:8080/pedidos
Método: POST
Exemplo de corpo JSON:

{
    "status": true,
    "quantidade": 2,
    "valor": 25.0,
    "dataHora": "2023-08-18T12:00:00",
    "funcionario": {
        "id": 1
    },
    "pizzas": [
        {
            "id": 1
        },
        {
            "id": 2
        }
    ],
    "produtos": [
        {
            "id": 1
        }
    ]
}
PUT /pedidos/{id}
Descrição: Atualiza informações de um pedido existente.
Exemplo de URL: http://localhost:8080/pedidos/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "status": false,
    "quantidade": 3,
    "valor": 30.0,
    "dataHora": "2023-08-18T13:30:00",
    "funcionario": {
        "id": 2
    },
    "pizzas": [
        {
            "id": 3
        }
    ],
    "produtos": [
        {
            "id": 2
        },
        {
            "id": 3
        }
    ]
}
DELETE /pedidos/{id}
Descrição: Exclui um pedido específico.
Exemplo de URL: http://localhost:8080/pedidos/{id}
Método: DELETE

Pizza Controller
GET /pizzas/{id}
Descrição: Recupera informações de uma pizza específica.
Exemplo de URL: http://localhost:8080/pizzas/{id}
Método: GET
GET /pizzas
Descrição: Recupera uma lista de todas as pizzas cadastradas.
Exemplo de URL: http://localhost:8080/pizzas
Método: GET
POST /pizzas
Descrição: Cria uma nova pizza.
Exemplo de URL: http://localhost:8080/pizzas
Método: POST
Exemplo de corpo JSON:

{
    "nome": "Pizza de Calabresa",
    "tamanho": "Média",
    "preco": 30.0,
    "sabores": [
        {
            "id": 1
        },
        {
            "id": 2
        }
    ]
}
PUT /pizzas/{id}
Descrição: Atualiza informações de uma pizza existente.
Exemplo de URL: http://localhost:8080/pizzas/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "nome": "Pizza de Calabresa",
    "tamanho": "Grande",
    "preco": 35.0,
    "sabores": [
        {
            "id": 1
        },
        {
            "id": 3
        }
    ]
}
DELETE /pizzas/{id}
Descrição: Exclui uma pizza específica.
Exemplo de URL: http://localhost:8080/pizzas/{id}
Método: DELETE
Produto Controller
GET /produtos/{id}
Descrição: Recupera informações de um produto específico.
Exemplo de URL: http://localhost:8080/produtos/{id}
Método: GET
GET /produtos
Descrição: Recupera uma lista de todos os produtos cadastrados.
Exemplo de URL: http://localhost:8080/produtos
Método: GET
POST /produtos
Descrição: Cria um novo produto.
Exemplo de URL: http://localhost:8080/produtos
Método: POST
Exemplo de corpo JSON:

{
    "nome": "Refrigerante",
    "descricao": "Bebida não alcoólica",
    "preco": 5.0
}
PUT /produtos/{id}
Descrição: Atualiza informações de um produto existente.
Exemplo de URL: http://localhost:8080/produtos/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "nome": "Refrigerante",
    "descricao": "Bebida não alcoólica - 500ml",
    "preco": 6.0
}
DELETE /produtos/{id}
Descrição: Exclui um produto específico.
Exemplo de URL: http://localhost:8080/produtos/{id}
Método: DELETE
Sabor Controller
GET /sabores/{id}
Descrição: Recupera informações de um sabor específico.
Exemplo de URL: http://localhost:8080/sabores/{id}
Método: GET
GET /sabores
Descrição: Recupera uma lista de todos os sabores cadastrados.
Exemplo de URL: http://localhost:8080/sabores
Método: GET
POST /sabores
Descrição: Cria um novo sabor.
Exemplo de URL: http://localhost:8080/sabores
Método: POST
Exemplo de corpo JSON:

{
    "nome": "Calabresa",
    "descricao": "Sabor clássico de calabresa com cebola e queijo"
}
PUT /sabores/{id}
Descrição: Atualiza informações de um sabor existente.
Exemplo de URL: http://localhost:8080/sabores/{id}
Método: PUT
Exemplo de corpo JSON:

{
    "id": {id},
    "nome": "Calabresa",
    "descricao": "Sabor clássico de calabresa com cebola e queijo, agora com mais queijo!"
}
DELETE /sabores/{id}
Descrição: Exclui um sabor específico.
Exemplo de URL: http://localhost:8080/sabores/{id}
Método: DELETE
Lembre-se de substituir {id} pelos valores reais dos IDs ao realizar as chamadas no Postman. 
