
# Gerenciador de Projetos

Este repositorio é de uma Api Rest criada para a trilha do projeto FullStack Codigo Certo.






## Referência

 - [Trilha FullStack Codigo Certo](https://github.com/codigocerto/TrilhaFullStackJR-JUN15)
 - [Front-End para consumir API](https://github.com/ViniciusANascimento/VanillaHTML-gerenciador-projeto)


## Documentação da API

#### Cadastro de usuario

```http
  POST /users
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `number` | ID gerada automaticamente |
| `username` | `string` | **Obrigatório**. Nome de Usuario de exibição |
| `password` | `string` | **Obrigatório**. Senha do Usuario |
| `email` | `string` | **Obrigatório**. Email que será usado como identificador da conta |

#### Cadastro de Projetos

```http
  POST /project
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `number` | ID gerada automaticamente |
| `userID` | `number` | **Obrigatório**. Codigo do usuario á quem pertence o projeto |
| `titulo` | `string` | **Obrigatório**. Titulo do projeto |
| `descricao` | `string` | **Obrigatório**. Descrição do projeto |
| `date` | `string` | **Obrigatório**. Formato:AAAA-MM-DD  |

```http
  GET /project/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `number` | ID de usuario cadastrado |

Retorna as informações do usuario através do ID fornecido.

```http
  PUT /project/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `number` | **Obrigatório**. ID do projeto |
| `userID` | `number` | **Obrigatório**. Codigo do usuario á quem pertence o projeto |
| `titulo` | `string` | **Obrigatório**. Titulo do projeto |
| `descricao` | `string` | **Obrigatório**. Descrição do projeto |
| `date` | `string` | **Obrigatório**. Formato:AAAA-MM-DD  |

```http
  DELETE /project/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `number` | **Obrigatório**. ID do projeto |


#### Credenciais de acesso
Para realização do login do usuario ao sistema.
```http
  POST /auth/{login}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `number` | ID gerada automaticamente |
| `password` | `string` | **Obrigatório**. Senha do Usuario |
| `email` | `string` | **Obrigatório**. Email que será usado como identificador da conta |

**Experimental.** Metodo para recuperação de senha.
```http
  GET /auth/{email}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` | Email Cadastrado para recuperação da senha. |




