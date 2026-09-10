CREATE TABLE usuario_role (
                              id_usuario BIGINT,
                              id_role BIGINT,
                              PRIMARY KEY (id_usuario, id_role),
                              CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
                              CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES role(id_role)
);