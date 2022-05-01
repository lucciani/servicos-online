set foreign_key_checks = 0;

delete from departamento;

set foreign_key_checks = 1;

alter table departamento auto_increment = 1;

INSERT INTO departamento (id, descricao, ativo) VALUES (1, 'Suporte', 1);
INSERT INTO departamento (id, descricao, ativo) VALUES (2, 'Infraestrutura',1);