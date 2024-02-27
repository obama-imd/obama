INSERT INTO curriculo (id, nome_abreviado, nome_completo) VALUES(1, 'PCN', 'Parâmetros Curriculares Nacional');
INSERT INTO curriculo (id, nome_abreviado, nome_completo) VALUES(2, 'BNCC', 'Base Nacional Comum Curricular');

INSERT INTO disciplina
(id, nome)
VALUES(1, 'Matemática');
INSERT INTO disciplina
(id, nome)
VALUES(2, 'Português');
INSERT INTO disciplina
(id, nome)
VALUES(3, 'História');
INSERT INTO disciplina
(id, nome)
VALUES(4, 'Física');
INSERT INTO disciplina
(id, nome)
VALUES(5, 'Geografia');
INSERT INTO disciplina
(id, nome)
VALUES(6, 'Quimica');

INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(1, 'Espaço e Forma', 1, 1);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(2, 'Grandezas e Medidas', 1, 1);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(3, 'Números e Operaçes/Álgebra e Funções', 1, 1);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(4, 'Tratamento da informação', 1, 1);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(5, 'Números', 1, 2);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(6, 'Álgebra', 1, 2);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(7, 'Geometria', 1, 2);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(8, 'Grandezas e medidas', 1, 2);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(9, 'Probabilidade e estatística', 1, 2);
INSERT INTO tema_conteudo
(id, nome, disciplina_id, curriculo_id)
VALUES(10, 'Não identificado', 1, 2);
