create table grupo (
                       id_grupo BIGINT primary key auto_increment,
                       nome VARCHAR(100) NOT NULL,
                       id_usuario_responsavel BIGINT,
                       ativo char(1) NOT NULL DEFAULT 'S',
                       CONSTRAINT fk_usuariDoGrupo FOREIGN KEY (id_usuario_responsavel ) REFERENCES usuario(id_usuario)
);

create table usuario_grupo(
                              id_usuario BIGINT,
                              id_grupo BIGINT,
                              ativo char(1) NOT NULL DEFAULT 'S',
                              PRIMARY KEY (id_usuario, id_grupo),
                              CONSTRAINT fk_usuario_grupo FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
                              CONSTRAINT fk_grupo_usuario FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo)
);