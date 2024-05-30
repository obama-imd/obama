CREATE TABLE plano_aula (
    id int8 PRIMARY KEY,
    data_cadastro TIMESTAMP NOT NULL,
    qtd_downloads INT NOT NULL DEFAULT 0,
    escola VARCHAR(255) NULL,
    duracao_em_minutos INT NULL,
    titulo VARCHAR(255) NOT NULL DEFAULT 'Plano sem título',
    resumo TEXT NULL,
    objetivo_geral TEXT NULL,
    objetivos_especificos TEXT NULL,
    metodologia TEXT NULL,
    referencias TEXT NULL,
    token TEXT UNIQUE NOT NULL,
    status ENUM('AGUARDANDO_REVISAO', 'NECESSARIO_AJUSTE', 'VALIDADO', 'RASCUNHO', 'REMOVIDO', 'SUGESTAO_REJEITADA'),
    autor_id int8 NOT NULL,
    ano_ensino_id int8 NULL,
    nivel_ensino_id int8 NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (nivel_ensino_id) REFERENCES nivel_ensino(id),
    FOREIGN KEY (ano_ensino_id) REFERENCES ano_ensino(id)
);

CREATE TABLE plano_aula_disciplina (
    plano_aula_id int8 NOT NULL,
    disciplina_id int8 NOT NULL,
    PRIMARY KEY (plano_aula_id, disciplina_id),
    FOREIGN KEY (plano_aula_id) REFERENCES plano_aula(id) ON DELETE CASCADE,
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id) ON DELETE CASCADE
);

CREATE TABLE plano_aula_coautor (
    plano_aula_id int8 NOT NULL,
    usuario_id int8 NOT NULL,
    PRIMARY KEY (plano_aula_id, usuario_id),
    FOREIGN KEY (plano_aula_id) REFERENCES plano_aula(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE plano_aula_objeto_aprendizagem (
    plano_aula_id int8 NOT NULL,
    objeto_aprendizagem_id int8 NOT NULL,
    PRIMARY KEY (plano_aula_id, objeto_aprendizagem_id),
    FOREIGN KEY (plano_aula_id) REFERENCES plano_aula(id) ON DELETE CASCADE,
    FOREIGN KEY (objeto_aprendizagem_id) REFERENCES objeto_aprendizagem(id) ON DELETE CASCADE
);

CREATE SEQUENCE sq_plano_aula_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;
