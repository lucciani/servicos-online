set foreign_key_checks = 0;

delete from departamento;
delete from categoria;
delete from prioridade;

set foreign_key_checks = 1;

alter table departamento auto_increment = 1;
alter table categoria auto_increment = 1;
alter table prioridade auto_increment = 1;

INSERT INTO departamento (id, descricao, ativo) VALUES (1, 'Suporte', 1);
INSERT INTO departamento (id, descricao, ativo) VALUES (2, 'Infraestrutura',1);

INSERT INTO categoria (id, descricao, ajuda, ativo, departamento_id) VALUES (1, 'Resetar senha de usuário', 'Solicitação para a alteração de senha do usuário de rede.', 1, 2);
INSERT INTO categoria (id, descricao, ajuda, ativo, departamento_id) VALUES (2, 'Criação de usuário de rede', 'Solicitação para a criação de usuário de rede.', 1, 2);
INSERT INTO categoria (id, descricao, ajuda, ativo, departamento_id) VALUES (3, 'Trocar teclado', 'Solicitação para a substituição de teclado.', 1, 1);

INSERT INTO prioridade (id, descricao) VALUES (1, 'Baixa');
INSERT INTO prioridade (id, descricao) VALUES (2, 'Media');
INSERT INTO prioridade (id, descricao) VALUES (3, 'Alta');