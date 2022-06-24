CREATE TABLE public.autor_mantenedor (
	id int8 NOT NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	site varchar(255) NULL,
	CONSTRAINT autor_mantenedor_pk PRIMARY KEY (id)
);


CREATE TABLE public.disciplina (
	id int8 NOT NULL,
	denominacao varchar(255) NULL,
	CONSTRAINT disciplina_pk PRIMARY KEY (id)
);


CREATE TABLE public.idioma (
	id int8 NOT NULL,
	idioma varchar(255) NULL,
	CONSTRAINT idioma_pk PRIMARY KEY (id)
);


CREATE TABLE public.nivelensino (
	id int8 NOT NULL,
	denominacao varchar(255) NULL,
	denominacao_abreviada varchar(255) NULL,
	CONSTRAINT nivelensino_pk PRIMARY KEY (id)
);


CREATE TABLE public.plataforma (
	id int8 NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT plataforma_pk PRIMARY KEY (id)
);


CREATE TABLE public.tipo_licenca_uso (
	id int8 NOT NULL,
	nome varchar(255) NULL,
	versao varchar(255) NULL,
	CONSTRAINT tipo_licenca_uso_pk PRIMARY KEY (id)
);


CREATE TABLE public.tipoobjeto (
	id int8 NOT NULL,
	tipo_objeto varchar(255) NULL,
	CONSTRAINT tipoobjeto_pk PRIMARY KEY (id)
);


CREATE TABLE public.usuario (
	id int8 NOT NULL,
	data_cadastro date NULL,
	email varchar(255) NOT NULL,
	nome varchar(255) NULL,
	perfil varchar(255) NULL,
	senha varchar(255) NULL,
	status_usuario varchar(255) NULL,
	tipo_cadastro varchar(255) NULL,
	"token" varchar(255) NULL,
	ultimo_acesso date NULL,
	CONSTRAINT usuario_uk01 UNIQUE (email),
	CONSTRAINT usuario_pK PRIMARY KEY (id)
);

CREATE TABLE public.comentario (
	id int8 NOT NULL,
	comentario_de_feedback bool NULL,
	data_comentario timestamp NULL,
	deletado bool NULL,
	texto text NULL,
	usuario_id int8 NULL,
	CONSTRAINT comentario_pk PRIMARY KEY (id),
	CONSTRAINT comentario_fk01 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id)
);


CREATE TABLE public.controleplanodeaula (
	id int8 NOT NULL,
	"token" varchar(255) NULL,
	usuario_id int8 NULL,
	CONSTRAINT controleplanodeaula_pk PRIMARY KEY (id),
	CONSTRAINT controleplanodeaula_fk01 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id)
);


CREATE TABLE public.evento (
	id int8 NOT NULL,
	ativo bool NULL,
	deletado bool NOT NULL,
	nome varchar(255) NULL,
	coordenador_id int8 NULL,
	CONSTRAINT evento_pk PRIMARY KEY (id),
	CONSTRAINT evento_fk FOREIGN KEY (coordenador_id) REFERENCES public.usuario(id)
);


CREATE TABLE public.evento_usuario (
	evento int8 NOT NULL,
	usuario_id int8 NOT NULL,
	CONSTRAINT evento_usuario_pkey PRIMARY KEY (evento, usuario_id),
	CONSTRAINT evento_fk01 FOREIGN KEY (evento) REFERENCES public.evento(id),
	CONSTRAINT evento_fk02 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id)
);


CREATE TABLE public.objetoaprendizagem (
	id int8 NOT NULL,
	ativo bool NULL,
	data_lancamento date NULL,
	descricao text NULL,
	link text NULL,
	nome varchar(255) NULL,
	path_arquivo varchar(255) NULL,
	qtd_acessos int4 NULL,
	tipo_visualizacao int4 NULL,
	versao varchar(255) NULL,
	id_tipo_licenca_uso int8 NULL,
	id_plataforma int8 NULL,
	id_tipo_objeto int8 NULL,
	CONSTRAINT objetoaprendizagem_pk PRIMARY KEY (id),
	CONSTRAINT objetoaprendizagem_fk01 FOREIGN KEY (id_tipo_licenca_uso) REFERENCES public.tipo_licenca_uso(id),
	CONSTRAINT objetoaprendizagem_fk02 FOREIGN KEY (id_tipo_objeto) REFERENCES public.tipoobjeto(id),
	CONSTRAINT objetoaprendizagem_fk03 FOREIGN KEY (id_plataforma) REFERENCES public.plataforma(id)
);


CREATE TABLE public.objetoaprendizagem_autormantenedor (
	objeto_aprendizagem_id int8 NOT NULL,
	autores_mantenedores_id int8 NOT NULL,
	CONSTRAINT objetoaprendizagem_autormantenedor_fk01 FOREIGN KEY (autores_mantenedores_id) REFERENCES public.autor_mantenedor(id),
	CONSTRAINT objetoaprendizagem_autormantenedor_fk02 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES public.objetoaprendizagem(id)
);


CREATE TABLE public.objetoaprendizagem_avaliacao (
	id int8 NOT NULL,
	data_avaliacao timestamp NULL,
	oa_id int8 NULL,
	usuario_id int8 NULL,
	CONSTRAINT objetoaprendizagem_avaliacao_pk PRIMARY KEY (id),
	CONSTRAINT objetoaprendizagem_avaliacao_fk01 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id),
	CONSTRAINT objetoaprendizagem_avaliacao_fk02 FOREIGN KEY (oa_id) REFERENCES public.objetoaprendizagem(id)
);

CREATE TABLE public.objetoaprendizagem_comentario (
	objeto_aprendizagem_id int8 NOT NULL,
	comentario_id int8 NOT NULL,
	CONSTRAINT objetoaprendizagem_comentario_pk PRIMARY KEY (objeto_aprendizagem_id, comentario_id),
	CONSTRAINT objetoaprendizagem_comentario_fk01 FOREIGN KEY (comentario_id) REFERENCES public.comentario(id),
	CONSTRAINT objetoaprendizagem_comentario_fk02 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES public.objetoaprendizagem(id)
);


CREATE TABLE public.objetoaprendizagem_idioma (
	objeto_aprendizagem_id int8 NOT NULL,
	idioma_id int8 NOT NULL,
	CONSTRAINT objetoaprendizagem_idioma_fk01 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES public.objetoaprendizagem(id),
	CONSTRAINT objetoaprendizagem_idioma_fk02 FOREIGN KEY (idioma_id) REFERENCES public.idioma(id)
);


CREATE TABLE public.planodeaula (
	id int8 NOT NULL,
	ano_ensino int4 NULL,
	avaliacao text NULL,
	data_cadastro timestamp NULL,
	duracao_em_minutos int4 NULL,
	escola varchar(255) NULL,
	metodologia text NULL,
	objetivo_geral text NULL,
	objetivos_especificos text NULL,
	qtd_downloads int4 NULL,
	referencias text NULL,
	resumo text NULL,
	status_id int4 NULL,
	titulo varchar(255) NULL,
	"token" text NOT NULL,
	autor_id int8 NULL,
	nivel_ensino_id int8 NULL,
	CONSTRAINT planodeaula_pk PRIMARY KEY (id),
	CONSTRAINT planodeaula_uk01 UNIQUE (token),
	CONSTRAINT planodeaula_fk01 FOREIGN KEY (nivel_ensino_id) REFERENCES public.nivelensino(id),
	CONSTRAINT planodeaula_fk02 FOREIGN KEY (autor_id) REFERENCES public.usuario(id)
);


CREATE TABLE public.planodeaula_coautor (
	planodeaula_id int8 NOT NULL,
	usuario_id int8 NOT NULL,
	CONSTRAINT planodeaula_coautor_pk PRIMARY KEY (planodeaula_id, usuario_id),
	CONSTRAINT planodeaula_coautor_fk01 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id),
	CONSTRAINT planodeaula_coautor_fk02 FOREIGN KEY (planodeaula_id) REFERENCES public.planodeaula(id)
);


CREATE TABLE public.planodeaula_comentario (
	plano_de_aula_id int8 NOT NULL,
	comentario_id int8 NOT NULL,
	CONSTRAINT planodeaula_comentario_pk PRIMARY KEY (plano_de_aula_id, comentario_id),
	CONSTRAINT planodeaula_comentario_fk01 FOREIGN KEY (plano_de_aula_id) REFERENCES public.planodeaula(id),
	CONSTRAINT planodeaula_comentario_fk02 FOREIGN KEY (comentario_id) REFERENCES public.comentario(id)
);


CREATE TABLE public.planodeaula_disciplina (
	plano_de_aula_id int8 NOT NULL,
	disciplina_envolvida_id int8 NOT NULL,
	CONSTRAINT planodeaula_disciplina_pk PRIMARY KEY (plano_de_aula_id, disciplina_envolvida_id),
	CONSTRAINT planodeaula_disciplina_fk01 FOREIGN KEY (disciplina_envolvida_id) REFERENCES public.disciplina(id),
	CONSTRAINT planodeaula_disciplina_fk02 FOREIGN KEY (plano_de_aula_id) REFERENCES public.planodeaula(id)
);


CREATE TABLE public.planodeaula_objetoaprendizagem (
	planodeaula_id int8 NOT NULL,
	objetosaprendizagem_id int8 NOT NULL,
	CONSTRAINT planodeaula_objetoaprendizagem_pk PRIMARY KEY (planodeaula_id, objetosaprendizagem_id),
	CONSTRAINT planodeaula_objetoaprendizagem_fk01 FOREIGN KEY (planodeaula_id) REFERENCES public.planodeaula(id),
	CONSTRAINT planodeaula_objetoaprendizagem_fk02 FOREIGN KEY (objetosaprendizagem_id) REFERENCES public.objetoaprendizagem(id)
);


CREATE TABLE public.sugestao_oa (
	id int8 NOT NULL,
	descricao text NULL,
	descritores text NULL,
	link varchar(255) NULL,
	nome varchar(255) NULL,
	status varchar(255) NULL,
	usuario_id int8 NULL,
	CONSTRAINT sugestao_oa_pk PRIMARY KEY (id),
	CONSTRAINT sugestao_oa_fk01 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id)
);


CREATE TABLE public.temaconteudo (
	id int8 NOT NULL,
	curriculo varchar(255) NULL,
	denominacao varchar(255) NULL,
	disciplina int8 NULL,
	CONSTRAINT temaconteudo_pk PRIMARY KEY (id),
	CONSTRAINT temaconteudo_fk01 FOREIGN KEY (disciplina) REFERENCES public.disciplina(id)
);


CREATE TABLE public.atividade (
	id int8 NOT NULL,
	ativo bool NULL,
	carga_horaria int4 NULL,
	data_final varchar(255) NULL,
	data_inicial varchar(255) NULL,
	estado varchar(255) NULL,
	"local" varchar(255) NULL,
	nome varchar(255) NULL,
	evento_id int8 NULL,
	CONSTRAINT atividade_pk PRIMARY KEY (id),
	CONSTRAINT atividade_fk01 FOREIGN KEY (evento_id) REFERENCES public.evento(id)
);

CREATE TABLE public.avaliacao_planodeaula (
	id int8 NOT NULL,
	resultado_avaliacao text NULL,
	id_plano int8 NULL,
	id_revisor int8 NULL,
	CONSTRAINT avaliacao_planodeaula_pk PRIMARY KEY (id),
	CONSTRAINT avaliacao_planodeaula_fk01 FOREIGN KEY (id_revisor) REFERENCES public.usuario(id),
	CONSTRAINT avaliacao_planodeaula_fk02 FOREIGN KEY (id_plano) REFERENCES public.planodeaula(id)
);

CREATE TABLE public.descritor (
	id int8 NOT NULL,
	codigo varchar(255) NULL,
	descricao varchar(255) NULL,
	nivelensino int8 NULL,
	temaconteudo int8 NULL,
	CONSTRAINT descritor_pk PRIMARY KEY (id),
	CONSTRAINT descritor_fk01 FOREIGN KEY (nivelensino) REFERENCES public.nivelensino(id),
	CONSTRAINT descritor_fk02 FOREIGN KEY (temaconteudo) REFERENCES public.temaconteudo(id)
);


CREATE TABLE public.habilidade (
	id int8 NOT NULL,
	ano_ensino int4 NULL,
	codigo varchar(255) NULL,
	conhecimentos text NULL,
	descricao text NULL,
	nivelensino int8 NULL,
	temaconteudo int8 NULL,
	CONSTRAINT habilidade_pk PRIMARY KEY (id),
	CONSTRAINT habilidade_fk01 FOREIGN KEY (temaconteudo) REFERENCES public.temaconteudo(id),
	CONSTRAINT habilidade_fk02 FOREIGN KEY (nivelensino) REFERENCES public.nivelensino(id)
);


CREATE TABLE public.objetoaprendizagem_descritor (
	objetoaprendizagem int8 NOT NULL,
	descritor_id int8 NOT NULL,
	CONSTRAINT objetoaprendizagem_descritor_pk PRIMARY KEY (objetoaprendizagem, descritor_id),
	CONSTRAINT objetoaprendizagem_descritor_fk01 FOREIGN KEY (objetoaprendizagem) REFERENCES public.objetoaprendizagem(id),
	CONSTRAINT objetoaprendizagem_descritor_fk02 FOREIGN KEY (descritor_id) REFERENCES public.descritor(id)
);


CREATE TABLE public.objetoaprendizagem_habilidade (
	objetoaprendizagem int8 NOT NULL,
	habilidade_id int8 NOT NULL,
	CONSTRAINT objetoaprendizagem_habilidade_pk PRIMARY KEY (objetoaprendizagem, habilidade_id),
	CONSTRAINT objetoaprendizagem_habilidade_fk01 FOREIGN KEY (habilidade_id) REFERENCES public.habilidade(id),
	CONSTRAINT objetoaprendizagem_habilidade_fk02 FOREIGN KEY (objetoaprendizagem) REFERENCES public.objetoaprendizagem(id)
);


CREATE TABLE public.objetoaprendizagem_planodeaula (
	objeto_aprendizagem_id int8 NOT NULL,
	planos_de_aula_id int8 NOT NULL,
	CONSTRAINT objetoaprendizagem_planodeaula_pk PRIMARY KEY (objeto_aprendizagem_id, planos_de_aula_id),
	CONSTRAINT objetoaprendizagem_planodeaula_fk01 FOREIGN KEY (objeto_aprendizagem_id) REFERENCES public.objetoaprendizagem(id),
	CONSTRAINT objetoaprendizagem_planodeaula_fk02 FOREIGN KEY (planos_de_aula_id) REFERENCES public.planodeaula(id)
);


CREATE TABLE public.participante (
	id int8 NOT NULL,
	cpf varchar(255) NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	tipo_participante varchar(255) NULL,
	"token" varchar(255) NULL,
	atividade_id int8 NULL,
	CONSTRAINT participante_pk PRIMARY KEY (id),
	CONSTRAINT participante_fk01 FOREIGN KEY (atividade_id) REFERENCES public.atividade(id)
);
