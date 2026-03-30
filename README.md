:key: Acessos Service — STUK E-commerce Microservices

Microserviço responsável por autenticação e autorização da arquitetura de e-commerce baseada em microserviços.

Este serviço gerencia:

Cadastro de usuários
Login
Geração de JWT
Refresh Token
Controle de Roles (ADMIN, CLIENTE, OPERADOR, etc)
Integração com API Gateway

----------------------------------------------------------------------------------------------------------------------------

:computer: Tecnologias utilizadas
Java 17+
Spring Boot
Spring Security
JWT
Spring Data JPA
PostgreSQL
Lombok
Maven
Docker (futuro)

----------------------------------------------------------------------------------------------------------------------------

:clipboard: Funcionalidades

:lock_with_ink_pen: Cadastro de usuário
Cria novos usuários
Define roles
Criptografa senha com BCrypt

:unlock: Login
Validação de credenciais
Geração de JWT
Retorno de token de acesso
Refresh Token
Geração de novo JWT
Mantém sessão ativa

:scroll: Roles
ADMIN
CLIENTE
OPERADOR
