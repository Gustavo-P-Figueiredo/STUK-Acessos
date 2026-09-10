CREATE TABLE permissao (
    id_permissao BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    ativo CHAR(1) NOT NULL DEFAULT 'S'
);

CREATE TABLE role_permissao (
id_role BIGINT NOT NULL,
id_permissao BIGINT NOT NULL,
PRIMARY KEY (id_role, id_permissao),
CONSTRAINT fk_role_permissao_role FOREIGN KEY (id_role) REFERENCES role(id_role),
CONSTRAINT fk_role_permissao_permissao FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao)
);