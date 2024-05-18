CREATE TABLE autor_mantenedor (
	id int8 NOT NULL,
	email varchar(255) NULL,
	nome varchar(255) NOT NULL,
	site varchar(255) NULL,
	CONSTRAINT autor_mantenedor_pk PRIMARY KEY (id)
);

CREATE TABLE disciplina (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT disciplina_pk PRIMARY KEY (id)
);


CREATE TABLE idioma (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT idiom_pk PRIMARY KEY (id)
);


CREATE TABLE nivel_ensino (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	nome_abreviado varchar(255) NOT NULL,
	CONSTRAINT nivel_ensino_pk PRIMARY KEY (id)
);

CREATE TABLE plataforma (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT plataforma_pk PRIMARY KEY (id)
);


CREATE TABLE tipo_licensa_uso (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	versao varchar(255) NOT NULL,
	CONSTRAINT tipo_licensa_uso_pk PRIMARY KEY (id)
);

CREATE TABLE ano_ensino (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	nivel_ensino_id int8 NOT NULL,
	CONSTRAINT ano_ensino_pk PRIMARY KEY (id),
	CONSTRAINT ano_ensino_fk01 FOREIGN KEY (nivel_ensino_id) REFERENCES nivel_ensino(id)
);


CREATE TABLE objeto_aprendizagem (
	id int8 NOT NULL,
	ativo bool NOT NULL,
	data_lancamento date NULL,
	descricao text NULL,
	nome varchar(255) NOT NULL,
	caminho_thumbnail varchar(255) NULL,
	quantidade_acessos int4 NOT NULL,
	versao varchar(255) NULL,
	tipo_licensa_uso_id int8 NULL,
	CONSTRAINT objeto_aprendizagem_pk PRIMARY KEY (id),
	CONSTRAINT objeto_aprendizagem_fk02 FOREIGN KEY (tipo_licensa_uso_id) REFERENCES tipo_licensa_uso(id)
);


CREATE TABLE objeto_aprendizagem_autor_mantenedor (
	objeto_aprendizagem_id int8 NOT NULL,
	autor_mantenedor_id int8 NOT NULL,
	CONSTRAINT objeto_aprendizagem_autor_mantenedor_pk PRIMARY KEY (objeto_aprendizagem_id, autor_mantenedor_id),
	CONSTRAINT objeto_aprendizagem_autor_mantenedor_fk01 FOREIGN KEY (autor_mantenedor_id) REFERENCES autor_mantenedor(id),
	CONSTRAINT objeto_aprendizagem_autor_mantenedor_fk02 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES objeto_aprendizagem(id)
);


CREATE TABLE objeto_aprendizagem_idioma (
	objeto_aprendizagem_id int8 NOT NULL,
	idioma_id int8 NOT NULL,
	CONSTRAINT objeto_aprendizagem_idioma_pk PRIMARY KEY (objeto_aprendizagem_id, idioma_id),
	CONSTRAINT objeto_aprendizagem_idioma_fk01 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES objeto_aprendizagem(id),
	CONSTRAINT objeto_aprendizagem_idioma_fk02 FOREIGN KEY (idioma_id) REFERENCES idioma(id)
);

CREATE TABLE tema_conteudo (
	id int8 NOT NULL,
	nome varchar(255) NOT NULL,
	curriculo varchar(255) NOT NULL,
	disciplina_id int8 NULL,
	CONSTRAINT tema_conteudo_pk PRIMARY KEY (id),
	CONSTRAINT tema_conteudo_fk01 FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);


CREATE TABLE descritor (
	id int8 NOT NULL,
	codigo varchar(255) NULL,
	descricao varchar(255) NULL,
	tema_conteudo_id int8 NULL,
	nivel_ensino_id int8 NULL,
	CONSTRAINT descritor_pk PRIMARY KEY (id),
	CONSTRAINT descritor_fk01 FOREIGN KEY (tema_conteudo_id) REFERENCES tema_conteudo(id),
	CONSTRAINT descritor_fk02 FOREIGN KEY (nivel_ensino_id) REFERENCES nivel_ensino(id)
);



CREATE TABLE habilidade (
	id int8 NOT NULL,
	codigo varchar(255) NULL,
	conhecimentos text NULL,
	descricao text NULL,
	ano_ensino_id int8 NULL,
	tema_conteudo_id int8 NULL,
	CONSTRAINT habilidade_pk PRIMARY KEY (id),
	CONSTRAINT habilidade_fk01 FOREIGN KEY (tema_conteudo_id) REFERENCES tema_conteudo(id),
	CONSTRAINT habilidade_fk02 FOREIGN KEY (ano_ensino_id) REFERENCES ano_ensino(id)
);


CREATE TABLE objeto_aprendizagem_descritor (
	objeto_aprendizagem_id int8 NOT NULL,
	descritor_id int8 NOT NULL,
	CONSTRAINT objeto_aprendizagem_descritor_pk PRIMARY KEY (objeto_aprendizagem_id, descritor_id),
	CONSTRAINT objeto_aprendizagem_descritor_fk01 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES objeto_aprendizagem(id),
	CONSTRAINT objeto_aprendizagem_descritor_fk02 FOREIGN KEY (descritor_id) REFERENCES descritor(id)
);

CREATE TABLE objeto_aprendizagem_habilidade (
	objeto_aprendizagem_id int8 NOT NULL,
	habilidade_id int8 NOT NULL,
	CONSTRAINT objeto_aprendizagem_habilidade_pk PRIMARY KEY (objeto_aprendizagem_id, habilidade_id),
	CONSTRAINT objeto_aprendizagem_habilidade_fk01 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES objeto_aprendizagem(id),
	CONSTRAINT objeto_aprendizagem_habilidade_fk02 FOREIGN KEY (habilidade_id) REFERENCES habilidade(id)
);

CREATE TABLE objeto_aprendizagem_plataforma (
	objeto_aprendizagem_id int8 NOT NULL,
	plataforma_id int8 NOT NULL,
	tipo_acesso varchar(255) NOT NULL,
	link text NOT NULL,
	CONSTRAINT objeto_aprendizagem_plataforma_fk01 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES objeto_aprendizagem(id),
	CONSTRAINT objeto_aprendizagem_plataforma_fk02 FOREIGN KEY (plataforma_id) REFERENCES plataforma(id)
);

CREATE SEQUENCE sq_ano_ensino_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_autor_mantenedor_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_curriculo_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_descritor_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_disciplina_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_habilidade_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_idioma_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_nivel_ensino_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_objeto_aprendizagem_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;


CREATE SEQUENCE sq_tema_conteudo_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;

CREATE SEQUENCE sq_tipo_licensa_uso_id INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999
MINVALUE 1
CACHE 10;
