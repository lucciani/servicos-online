set foreign_key_checks = 0;

delete from departamento;
delete from categoria;
delete from prioridade;
delete from status;
delete from permissao;
delete from grupo;

set foreign_key_checks = 1;

alter table departamento auto_increment = 1;
alter table categoria auto_increment = 1;
alter table prioridade auto_increment = 1;
alter table status auto_increment = 1;
alter table permissao auto_increment = 1;
alter table grupo auto_increment = 1;

INSERT INTO departamento (id, descricao, ativo) VALUES (1, 'Suporte', 1);
INSERT INTO departamento (id, descricao, ativo) VALUES (2, 'Infraestrutura',1);

INSERT INTO categoria (id, descricao, ajuda, ativo, departamento_id) VALUES (1, 'Resetar senha de usuário', 'Solicitação para a alteração de senha do usuário de rede.', 1, 2);
INSERT INTO categoria (id, descricao, ajuda, ativo, departamento_id) VALUES (2, 'Criação de usuário de rede', 'Solicitação para a criação de usuário de rede.', 1, 2);
INSERT INTO categoria (id, descricao, ajuda, ativo, departamento_id) VALUES (3, 'Trocar teclado', 'Solicitação para a substituição de teclado.', 1, 1);

INSERT INTO prioridade (id, descricao) VALUES (1, 'Baixa');
INSERT INTO prioridade (id, descricao) VALUES (2, 'Media');
INSERT INTO prioridade (id, descricao) VALUES (3, 'Alta');

INSERT INTO status (id, descricao) VALUES (1, 'Novo');
INSERT INTO status (id, descricao) VALUES (2, 'Atribuido');
INSERT INTO status (id, descricao) VALUES (3, 'Pendente');
INSERT INTO status (id, descricao) VALUES (4, 'Resolvido');
INSERT INTO status (id, descricao) VALUES (5, 'Aprovado');
INSERT INTO status (id, descricao) VALUES (6, 'Cancelado');
INSERT INTO status (id, descricao) VALUES (7, 'Fechado');

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_STATUS', 'Permite consultar status');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_STATUS', 'Permite editar status');

INSERT INTO grupo (id, nome) VALUES (1, 'Administrador'), (2, 'Gestor'), (3, 'Tecnico'), (4, 'Analista'), (5, 'Solicitante');