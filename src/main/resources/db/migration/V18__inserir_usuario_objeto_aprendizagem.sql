CREATE TABLE usuario_objeto_aprendizagem_favorito (
    usuario_id BIGINT NOT NULL,
    objeto_aprendizagem_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, objeto_aprendizagem_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) ON DELETE CASCADE,
    CONSTRAINT fk_objeto_aprendizagem FOREIGN KEY (objeto_aprendizagem_id)
        REFERENCES objeto_aprendizagem (id) ON DELETE CASCADE
);
