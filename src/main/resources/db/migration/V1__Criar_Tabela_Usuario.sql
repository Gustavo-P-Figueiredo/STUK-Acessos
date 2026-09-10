create table usuario (
                         id_usuario BIGINT primary key auto_increment,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         ativo char(1) NOT NULL DEFAULT 'S',
                         tentativas_login int DEFAULT 0
);