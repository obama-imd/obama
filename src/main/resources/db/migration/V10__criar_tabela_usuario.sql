CREATE TABLE Usuario (
    id int8 NOT NULL,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    papel ENUM('ADMIN', 'PADRAO', 'REVISOR') NOT NULL,
    ativo BOOLEAN NOT NULL,
    tipoCadastro ENUM('PADRAO', 'GOOGLE', 'FACEBOOK') NOT NULL,
    token VARCHAR(255) NOT NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (id)
);
