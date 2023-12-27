CREATE INDEX objeto_aprendizagem_idx1 ON objeto_aprendizagem USING btree (nome);
CREATE INDEX objeto_aprendizagem_idx2 ON objeto_aprendizagem USING btree (ativo);
CREATE INDEX objeto_aprendizagem_plataforma_idx1 ON objeto_aprendizagem_plataforma USING btree (tipo_acesso);
