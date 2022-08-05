CREATE INDEX objetoaprendizagem_idx1 ON objetoaprendizagem USING btree (nome);
CREATE INDEX objetoaprendizagem_idx2 ON objetoaprendizagem USING btree (ativo);
CREATE INDEX habilidade_idx2 ON habilidade USING btree (tema_conteudo_id);
CREATE INDEX descritor_idx1 ON descritor USING btree (nivel_ensino_id);
CREATE INDEX descritor_idx2 ON descritor USING btree (tema_conteudo_id);
CREATE INDEX anoensino_idx1 ON anoensino USING btree (nivel_ensino_id);
