CREATE TABLE permissao (
	id bigint NOT NULL auto_increment,
	descricao varchar(100) NOT NULL,
	nome varchar(60) NOT NULL,
	
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;